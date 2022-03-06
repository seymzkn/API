package get_http_request;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class practise {
     /*
    Positive Scenario:
    When I send a GET request to REST API URL
    https://restful-booker.herokuapp.com/booking/5
     Then
    HTTP Status Code 200
    And Responce in format "application/JSON"
    And "firstname" should be "Jim"
    And "totalprice" should be 224
    And "checkin" should be "2021-07-31"
     */

    @Test
    public void get01(){
        String url="https://restful-booker.herokuapp.com/booking/5";

        Response response=given().when().get(url);

        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Mary"),
                        "totalprice",equalTo(353),
                        "bookingdates.checkin",equalTo("2015-02-16"));

        JsonPath jsonPath=response.jsonPath();
    }
}
