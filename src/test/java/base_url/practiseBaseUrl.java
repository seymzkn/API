package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class practiseBaseUrl {

    protected RequestSpecification spec01;

    @Before
    public void setUp(){

        spec01=new RequestSpecBuilder().setBaseUri("http://dummy.restapiexample.com").build();

    }


}
