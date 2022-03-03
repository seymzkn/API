package get_http_request;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest02 {

    @Test
    public void test02(){

        String url="https://reqres.in/api/users";

        Response response=given().when().get(url);

        //response.prettyPrint();       responce daki her seyi getirir

        response.prettyPeek();        //responce daki her seyi getirir

        //response.then().log().all(); responce daki her seyi getirir usttekilerle ayni mantikta calisan koddur

        //Headers i test edelim
        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json; charset=utf-8").
                statusLine("HTTP/1.1 200 OK");

        //Body i test ediyoruz
        /*response.then().body("data[0].first_name", Matchers.equalTo("George"),
                "data[0].last_name",Matchers.equalTo("Bluth")
                ,"data[0].email",Matchers.equalTo("george.bluth@reqres.in"));

         */

        response.then().body("data[0].first_name", equalTo("George"),
                "data[0].last_name",equalTo("Bluth")
                ,"data[0].email",equalTo("george.bluth@reqres.in"));

        response.then().body("data[1].id",equalTo(2)
                        ,"data[1].email",equalTo("janet.weaver@reqres.in")
                        ,"data[1].first_name",equalTo("Janet")
                        ,"data[1].last_name",equalTo("Weaver")
                        ,"data[1].avatar",equalTo("https://reqres.in/img/faces/2-image.jpg"));

    }
}
