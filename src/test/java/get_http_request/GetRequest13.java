package get_http_request;

import base_url.GMIBankBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static get_http_request.Authentication.generateToken;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest13 extends GMIBankBaseUrl {

    /*
        http://www.gmibank.com/api/tp-customers/114351 adresindeki müşteri bilgilerini doğrulayın
        “firstName”: “Della”,
        “lastName”: “Heaney”,
        “mobilePhoneNumber”: “123-456-7893”,
        “address”: “75164 McClure Stream”,
        “country” : “USA”
        “state”: “New York43"
        “CREDIT_CARD”,hesabında 69700$ ,
        “CHECKING” hesabında 11190$
     */

    @Test
    public void test13() {

        spec03.pathParams("bir", "tp-customers", "iki", "114351");

        Response response = given().
                spec(spec03).
                header("Authorization", "Bearer " + generateToken()).
                when().
                get("/{bir}/{iki}");

        // /{bir}/{iki} => /tp-customers/114351

        response.prettyPrint();

        // MATCHERS CLASS ILE
        response.then().assertThat().body("firstName", equalTo("Della"),
                "lastName", equalTo("Heaney"),
                "mobilePhoneNumber", equalTo("123-456-7893"),
                "address", equalTo("75164 McClure Stream"),
                "country.name", equalTo("USA"),
                "state", equalTo("New York43"),
                "accounts[0].balance", equalTo(11190),
                "accounts[1].balance", equalTo(69700));

        // JSON PATH ILE DOGRULAMA
        JsonPath json = response.jsonPath();

        Assert.assertEquals("Della", json.getString("firstName"));
        Assert.assertEquals("Heaney", json.getString("lastName"));
        Assert.assertEquals("123-456-7893", json.getString("mobilePhoneNumber"));
        Assert.assertEquals("75164 McClure Stream", json.getString("address"));
        Assert.assertEquals("USA", json.getString("country.name"));
        Assert.assertEquals("New York43", json.getString("state"));
        Assert.assertEquals(11190, json.getInt("accounts[0].balance"));
        Assert.assertEquals(69700, json.getInt("accounts[1].balance"));
    }
}
