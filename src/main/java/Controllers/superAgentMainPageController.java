package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Status: empty, needs work.
 * TODO: finish up functionality for this - need to redo superAgent functionality + UI pages
 * TODO: is this what we really want?
 */
public class superAgentMainPageController extends UIController{

    public slideshowController slideshow = new slideshowController();

    @FXML
    public HBox imagebox;
    @FXML
    private Button reviewLabelButton, userSearchButton, createAgentButton;

    @FXML
    public void setDisplayApplicationReview() throws IOException {
        Stage stage;
        stage=(Stage) reviewLabelButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("applicationStatusForApplicant.fxml"));
        System.out.println(loader.getLocation().getPath());
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        applicationStatusForApplicantController controller = loader.getController();
        slideshow.stopAnimation();

    }

    @FXML
    public void setDisplayToSuperAgentSearchUsers() throws IOException {
        Stage stage;
        stage=(Stage) userSearchButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("superAgentSearchUsers.fxml"));
        System.out.println(loader.getLocation().getPath());
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        superAgentSearchUsersController controller = loader.getController();
        controller.initUserAuthenticationChoiceBox();
        controller.displayResults();
        slideshow.stopAnimation();

    }

    // TODO: change to createUser page? is this what we really want?
    @FXML
    public void setDisplayToSuperAgentCreateAgent() throws IOException {
        Stage stage;
        stage=(Stage) createAgentButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("superAgentCreateAgent.fxml"));
        System.out.println(loader.getLocation().getPath());
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        superAgentCreateAgentController controller = loader.getController();
        slideshow.stopAnimation();

    }

    @FXML
    private void setDisplayToSuperAgentSearchApplications() throws IOException {
        Stage stage;
        stage=(Stage) backButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("superAgentSearchApplications.fxml"));
        System.out.println(loader.getLocation().getPath());
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        superAgentSearchApplicationsController controller = loader.getController();
        controller.initsuperAgentApplicationTableView();
        slideshow.stopAnimation();

    }


}
