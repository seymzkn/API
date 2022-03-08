package get_http_request;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import sun.security.x509.FreshestCRLExtension;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest20 extends JsonPlaceHolderBaseUrl {
     /*
    https://jsonplaceholder.typicode.com/todos/2
    1) Status kodunun 200,
    2) respose body'de,
             "completed": değerinin false
         "title": değerinin "quis ut nam facilis et officia qui"
         "userId" sinin 1 ve
    header değerlerinden
         "via" değerinin "1.1 vegur" ve
         "Server" değerinin "cloudflare" olduğunu test edin…
    */

    @Test
    public void test20(){
        //1- URL olustur
        spec04.pathParams("paramatre1","todos","parametre2","2");

        //2- Expected data olustur
        HashMap<String,Object>expectedData=new HashMap<>();
        expectedData.put("statusCode",200);
        expectedData.put("completed",false);
        expectedData.put("userId",1);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("via","1.1 vegur");
        expectedData.put("Server","cloudflare");

        //3- Request ve Responce
        Response response=given().spec(spec04).when().get("/{parametre1}/{parametre2}");
        response.prettyPrint();

        //4- Dogrulama
        //1. Yol Matchers Class
        response.then().assertThat().statusCode((Integer) expectedData.get("statusCode")).
                headers("via",equalTo(expectedData.get("via")),
                        "Server",equalTo(expectedData.get("Server"))).
                body("userId",equalTo(expectedData.get("userId")),
                        "title",equalTo(expectedData.get("title")),
                        "completed",equalTo(expectedData.get("completed")));


        //2. Yol Json Path
    }
}
