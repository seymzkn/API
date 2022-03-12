package post_request;

import base_url.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostRequest02 extends DummyBaseUrl {

            /*
        http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
        {
            "name":"Ali Can",
            "salary":"2000",
            "age":"40",
        }
        gönderildiğinde,Status kodun 200 olduğunu ve dönen response body nin,

        {
            "status": "success",
            "data": {
            "id":…
        },
            "message": "Successfully! Record has been added."
        }

        olduğunu test edin
         */

    @Test
    public void test02(){

        //URL olustur
        spec02.pathParams("bir","api","iki","v1","uc","create");

        //2)EXPECTED DATA
        DummyTestData obje=new DummyTestData();

        //Request icin, gonderdigimiz post edecegimiz data
        HashMap<String,Object>requestBodyMap=obje.setUpRequestBody();

        //Expected Data icin post ettikten sonra gelen cevap
        HashMap<String,Object>expectedDataMap=obje.setUpExpectedData();
        System.out.println("expectedDataMap = " + expectedDataMap);


        //3)REQUEST VE RESPONCE
        Response response=given().
                            spec(spec02).
                            body(requestBodyMap).
                            when().
                            post("/{bir}/{iki}/{uc}");

        // POST isleminde Map kullandigimizda toString e gerek yoktur.toString sadce jsonObject de
        response.prettyPrint();


        //4)DOGRULAMA
        //De-Serialization
        HashMap<String,Object>actualDataMap=response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);

        Assert.assertEquals(expectedDataMap.get("statusCode"),response.statusCode());
        Assert.assertEquals(expectedDataMap.get("message"),actualDataMap.get("message"));
        Assert.assertEquals(expectedDataMap.get("status"),actualDataMap.get("status"));

        //JSON Path ile
        JsonPath json=response.jsonPath();

        Assert.assertEquals(expectedDataMap.get("statudCode"),response.statusCode());
        Assert.assertEquals(expectedDataMap.get("message"),json.getString("message"));
        Assert.assertEquals(expectedDataMap.get("status"),json.getString("status"));
    }



}
