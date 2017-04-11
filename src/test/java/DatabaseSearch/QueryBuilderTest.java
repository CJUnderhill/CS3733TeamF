package DatabaseSearch;

//import org.junit.Test;

//import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Created by Chad on 4/3/2017.
 */
public class QueryBuilderTest {

    QueryBuilder individualAppQuery = new QueryBuilder("APPLICATION", "*", "1");
    QueryBuilder searchAppQuery = new QueryBuilder("APPLICATION", "*", "01/01/2000", "01/01/2017",
            "Coldsnap", "Super Freeze", "000", "999", "MA");
    QueryBuilder userQuery = new QueryBuilder("USERS", "*", "testUser", "password");

    static String iaSQL = "SELECT * FROM APP.APPLICATION WHERE APP_ID=1";
    static String saSQL = "SELECT * FROM APP.APPLICATION WHERE DATE BETWEEN '01/01/2000' AND '01/01/2017'" +
            " AND BRAND_NAME='Coldsnap' AND PRODUCT_NAME='Super Freeze' AND TYPE BETWEEN 000 AND 999 AND ORIGIN_CODE='MA'";
    static String uSQL = "SELECT * FROM APP.USERS WHERE LOGIN_NAME='testUser' AND PASSWORD='password'";

//    @Test
//    public void getIndividualAppQueryTest() {
//        assertEquals(iaSQL, individualAppQuery.getQuery());
//    }
//
//    @Test
//    public void getSearchAppQueryTest() {
//        assertEquals(saSQL, searchAppQuery.getQuery());
//    }
//
//    @Test
//    public void getUserQueryTest() {
//        assertEquals(uSQL, userQuery.getQuery());
//    }

}