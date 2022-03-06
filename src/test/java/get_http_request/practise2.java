package get_http_request;

import base_url.practiseBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class practise2 extends practiseBaseUrl {

     /*
   http://dummy.restapiexample.com/api/v1/employees url'inde bulunan

  1) Butun calisanlarin isimlerini consola yazdıralim
  2) 3. calisan kisinin ismini konsola yazdıralim
  3) Ilk 5 calisanin adini konsola yazdiralim
  4) En son calisanin adini konsola yazdiralim
    */

    @Test
    public void get02(){

        spec01.pathParams("parametre1","api","parametre2","v1","parametre3","employees");

        //Response response=given().spec(spec01).get("/api/v1/employees");

        Response response=given().spec(spec01).when().get("/{parametre1}/{parametre2}/{parametre3}");

        response.prettyPrint();

        JsonPath jsonPath=response.jsonPath();

        System.out.println(jsonPath.getString("data[2].employee_name"));

        //5. calisanin isminin "Airi Satou" oldugunu dogrulayin

        Assert.assertEquals("Airi Satou",jsonPath.getString("data[4].employee_name"));

        //7. calisanin isminin "Herrod" icerip icermdigini dogrulayin
        Assert.assertTrue(jsonPath.getString("data[6].employee_name").contains("Herrod"));

        //9. calisanin yasinin 39 oldugunu dogrulayin

        Assert.assertEquals(39,jsonPath.getInt("data[8].employee_age"));

        //24 tane calisan oldugunu dogrulayiniz

        //Assert.assertEquals(24,jsonPath.getList("data.employee_name").size());

        //===============hasSize hasItem hasItems=====================

        response.then().assertThat().body("data.employee_name", Matchers.hasSize(24),
                                            "data.employee_name",Matchers.hasItem("Gloria Little"),
                                                "data.employee_age",Matchers.hasItems(61,63,66));




    }


}
