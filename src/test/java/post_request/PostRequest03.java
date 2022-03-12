package post_request;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.internal.ResponseSpecificationImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequest03 extends JsonPlaceHolderBaseUrl {

    /*
       https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
        {
        "userId": 55,
        "title": "Tidy your room",
        "completed": false
      }
        Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin
      {
        "userId": 55,
        "title": "Tidy your room",
        "completed": false,
        "id":201
       }
    */

    @Test
    public void test03(){
        //1)URL OLUSTUR
        spec04.pathParam("bir","todos");


        //2)EXPECTED DATA
        JsonPlaceHolderTestData testObje=new JsonPlaceHolderTestData();
        JSONObject expectedRequestData=testObje.setUpPostData();
        System.out.println("expectedRequestData = " + expectedRequestData);


        //3)REQUEST VE RESPONCE

        Response response = given().
                contentType(ContentType.JSON).
                spec(spec04).
                body(expectedRequestData.toString()).
                when().
                post("/{bir}");

        response.prettyPrint();

        //4) DOGRULAMA

        //1.YOL MATCHERS CLASS

        response.then().assertThat().statusCode(201).body("userId",equalTo(expectedRequestData.get("userId")),
                            "title",equalTo(expectedRequestData.get("title")),
                            "completed",equalTo(expectedRequestData.get("completed")),
                            "id",equalTo(expectedRequestData.get("id")));

        //2.YOL JSON PATH
        JsonPath json=response.jsonPath();

        Assert.assertEquals(expectedRequestData.get("statusCode"),response.statusCode());
        Assert.assertEquals(expectedRequestData.get("userId"),json.get("userId"));
        Assert.assertEquals(expectedRequestData.get("title"),json.get("title"));
        Assert.assertEquals(expectedRequestData.get("completed"),json.getBoolean("completed"));
        Assert.assertEquals(expectedRequestData.get("id"),json.getInt("id"));

        //3. De-Serialization
        HashMap<String,Object>actualDataMap=response.as(HashMap.class);
        Assert.assertEquals(expectedRequestData.get("id"),actualDataMap.get("id"));
        Assert.assertEquals(expectedRequestData.get("userId"),actualDataMap.get("userId"));
        Assert.assertEquals(expectedRequestData.get("statusCode"),actualDataMap.get("statusCode"));
        Assert.assertEquals(expectedRequestData.get("title"),actualDataMap.get("title"));
        Assert.assertEquals(expectedRequestData.get("completed"),actualDataMap.get("completed"));


    }
}
