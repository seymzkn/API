package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class GMIBankBaseUrl {


        protected RequestSpecification spec03;

        @Before
        public void setup(){
            spec03 = new RequestSpecBuilder().setBaseUri("http://www.gmibank.com/api").build();
        }
}
