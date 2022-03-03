package get_http_request;


import io.restassured.http.ContentType;
import io.restassured.response.Response;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest05 {

    /*
    https://jsonplaceholder.typicode.com/todos/123 url’ine
   accept type’i “application/json” olan GET request’i yolladigimda
   gelen response’un
			status kodunun 200
   		ve content type’inin “application/json”
			ve Headers’daki “Server” in “cloudflare”
			ve response body’deki “userId”’nin 7
			ve “title” in “esse et quis iste est earum aut impedit”
			ve “completed” bolumunun false oldugunu test edin
     */

    @Test
    public void test05(){
        String url="https://jsonplaceholder.typicode.com/todos/123";

        Response response=given().when().get(url);

        response.prettyPrint();

        response.then().contentType(ContentType.JSON).statusCode(200).headers("Server",Matchers.equalTo("cloudflare")).
                headers("Pragma",Matchers.equalTo("no-cache"));

        response.then().assertThat().headers("Server",Matchers.equalTo("cloudflare")).
                body("userId",Matchers.equalTo(7),
                        "title",Matchers.equalTo("esse et quis iste est earum aut impedit"),
                "completed",Matchers.equalTo(false));
    }
}
