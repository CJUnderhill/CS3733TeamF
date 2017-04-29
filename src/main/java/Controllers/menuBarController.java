package Controllers;

import DBManager.DBManager;
import DatabaseSearch.AppRecord;
import UserAccounts.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Ethan on 4/26/2017.
 */
public class menuBarController extends UIController {

    private searchPageController searchPageController;
    //Variables to hold the appropriate FXML buttons and fields
    @FXML
    private Button backButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button aboutButton;
    @FXML
    private TextField searchBar;

    DBManager db = new DBManager();


    public void setSearchPageController(searchPageController controller){
        this.searchPageController = controller;

    }

    @FXML
    private void setDisplayToLoginPage() throws IOException {
            String loginStatus = loginButton.getText();
        if (loginStatus.equals("LOGIN")) {
            BorderPane borderPane = main.getBorderPane();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("loginPage.fxml"));
            AnchorPane anchorPane = loader.load();
            Stage stage;
            stage=(Stage) loginButton.getScene().getWindow();
            Scene scene = borderPane.getScene();
            stage.setScene(scene);
            borderPane.setTop(menuBarSingleton.getInstance().getBar());
            borderPane.setBottom(anchorPane);
            stage.show();
            loginPageController controller = loader.getController();
            controller.init(main);
            loginButton.setText("LOGOUT");
        } else {
            BorderPane borderPane = main.getBorderPane();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("mainPage.fxml"));
            AnchorPane anchorPane = loader.load();
            Stage stage;
            stage=(Stage) loginButton.getScene().getWindow();
            Scene scene = borderPane.getScene();
            stage.setScene(scene);
            borderPane.setTop(menuBarSingleton.getInstance().getBar());
            borderPane.setBottom(anchorPane);
            stage.show();
            mainPageController controller = loader.getController();
            controller.init(main);
            loginButton.setText("LOGIN");
        }
    }

    /**
     * Redirects to searchResultsPage.fxml TODO: make sure this is correct
     * @throws IOException - throws exception
     */
    @FXML
    private void setDisplayToSearchFromOffPage() throws IOException{
        BorderPane borderPane = main.getBorderPane();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("searchPage.fxml"));
        ScrollPane anchorPane = loader.load();
        Stage stage;
        stage=(Stage) searchButton.getScene().getWindow();
        Scene scene = borderPane.getScene();
        stage.setScene(scene);
        borderPane.setTop(menuBarSingleton.getInstance().getBar());
        borderPane.setBottom(anchorPane);
        stage.show();
        searchPageController controller = loader.getController();
        controller.init(main);
        controller.initApplicationTableView();
        controller.displayData(searchFromOffPage());
    }


    @FXML
    protected void setDisplayToAboutPage() throws IOException {
        BorderPane borderPane = main.getBorderPane();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("aboutPage.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage stage;
        stage=(Stage) aboutButton.getScene().getWindow();
        Scene scene = borderPane.getScene();
        stage.setScene(scene);
        borderPane.setTop(menuBarSingleton.getInstance().getBar());
        borderPane.setBottom(anchorPane);
        stage.show();
        aboutPageController controller = loader.getController();
        controller.init(main);
    }

    @FXML
    private ObservableList<AppRecord> searchFromOffPage() {
        //Set all variables equal to input data
        String searchBarContent = searchBar.getText();
        try {
            String params = " WHERE STATUS = 'Accepted' AND";
            params += " (UPPER(BRAND_NAME) LIKE UPPER('%" + searchBarContent + "%') OR UPPER(FANCIFUL_NAME) LIKE UPPER('%" + searchBarContent + "%'))";

            ArrayList<ArrayList<String>> searchParams = new ArrayList<>();

            ObservableList<AppRecord> arr = db.findLabels(searchParams, params);
            System.out.println("ARR IS " + arr);
            main.userData.setObservableList(arr);
            System.out.println("USERDATA IS " + main.userData.getObservableList());
            return arr;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not build a query from search criteria.");
            return null;
        }
    }

    @FXML
    public ObservableList<AppRecord> simpleSearch(boolean isMalt, boolean isWine, boolean isSpirit){
        try {
            //Set all variables equal to input data
            String searchBarContent = searchBar.getText();

            boolean firstCheck = false;

            String params = " WHERE STATUS = 'Accepted' AND";

            if (isMalt || isSpirit || isWine) {

                params += " (ALCOHOL_TYPE = ";

                if (isWine) {
                    params += "'Wine'";
                    firstCheck = true;
                }

                if (isSpirit && !firstCheck) {
                    params += "'Distilled Spirits'";
                    firstCheck = true;
                } else if (isSpirit && firstCheck) {
                    params += " OR ALCOHOL_TYPE = 'Distilled Spirits'";
                }

                if (isMalt && !firstCheck) {
                    params += "'Malt Beverages'";
                    firstCheck = true;
                } else if (isMalt && firstCheck) {
                    params += " OR ALCOHOL_TYPE = 'Malt Beverages'";
                }
                params += ")";
            }
            params += " AND (UPPER(BRAND_NAME) LIKE UPPER('%" + searchBarContent + "%') OR UPPER(FANCIFUL_NAME) LIKE UPPER('%" + searchBarContent + "%'))";

            ArrayList<ArrayList<String>> searchParams = new ArrayList<>();

            ObservableList<AppRecord> arr = db.findLabels(searchParams, params);
            System.out.println("ARR IS " + arr);
            main.userData.setObservableList(arr);
            System.out.println("USERDATA IS " + main.userData.getObservableList());

            return arr;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not build a query from search criteria.");
            return null;
        }
    }
}
