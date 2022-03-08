package get_http_request;

import base_url.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest19 extends DummyBaseUrl {

    /*
    http://dummy.restapiexample.com/api/v1/employees
    1) Status kodunun 200,
    2) 10’dan büyük tüm id'leri ekrana yazdırın ve 10’dan büyük 14 id olduğunu,
    3) 30’dan küçük tüm yaşları ekrana yazdırın ve bu yaşların içerisinde en büyük yaşın 23 olduğunu
    4) Maası 350000 den büyük olan tüm employee name'leri ekrana yazdırın
         ve bunların içerisinde "Charde Marshall" olduğunu test edin
    */

    @Test
    public void test19(){

        // 1- URL olustur
        spec02.pathParams("1","api","2","v1","3","employees");

        //http://dummy.restapiexample.com request den onceki adresimiz
        Response response=given().spec(spec02).when().get("/{1}/{2}/{3}");
        //"/{1}/{2}/{2}" -> /api/v1/employees

        response.prettyPrint();

        //1) Status kodunun 200,
        Assert.assertEquals(200,response.statusCode());

        //2) 10’dan büyük tüm id'leri ekrana yazdırın ve 10’dan büyük 14 id olduğunu,

        JsonPath json=response.jsonPath();

        List<Integer>idList=json.getList("data.findAll{it.id>10}.id");

        System.out.println(idList);
        //Groovy Java platformu uzerinde calisan bir bilgisayar dilidir.
        //Groovy ile loop kullanmadan response dan gelen degerleri bir sarta gore alabiliriz

        //3) 30’dan küçük tüm yaşları ekrana yazdırın ve bu yaşların içerisinde en büyük yaşın 23 olduğunu
        List<Integer>ageList=json.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println(ageList);

        Collections.sort(ageList);

        Assert.assertTrue(ageList.get(ageList.size()-1)==23);

        //4) Maası 350000 den büyük olan tüm employee name'leri ekrana yazdırın
        //ve bunların içerisinde "Charde Marshall" olduğunu test edin

        List<Integer>salaryList=json.getList("data.findAll{it.employee_salary>350000}.employee_name");
        System.out.println(salaryList);

        Assert.assertTrue(salaryList.contains("Charde Marshall"));


    }


}
