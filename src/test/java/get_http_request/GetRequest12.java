package get_http_request;

import io.restassured.response.Response;
import org.junit.Test;

import static get_http_request.Authentication.generateToken;
import static io.restassured.RestAssured.given;

public class GetRequest12 {

    //Auhenticatuon Class in icerisindeki generatToken() methodu kullanilacak

    String endPoint="http://www.gmibank.com/api/tp-customers";

    @Test
    public void test12(){
        Response response=given().
                header("Authorization","Bearer"+generateToken()).
                when().get(endPoint).
                then().
                extract().
                response();
    }
}
