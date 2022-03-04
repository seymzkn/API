package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.junit.Before;
import org.junit.runners.Parameterized;

public class RegresinBaseUrl {

   public RequestSpecification spec01;

    @Before
    public void setUp(){
        spec01=new RequestSpecBuilder().setBaseUri("https://reqres.in").build();
    }
}
