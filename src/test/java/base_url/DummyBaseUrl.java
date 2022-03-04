package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.junit.Before;
import org.junit.runners.Parameterized;

public class DummyBaseUrl {
    protected RequestSpecification spec02;

    @Before
    public void setUp(){

        spec02 = new RequestSpecBuilder().setBaseUri("http://dummy.restapiexample.com").build();
    }
}
