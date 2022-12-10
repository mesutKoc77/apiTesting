package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;


public class DummyBaseUrl {

    //spec oluşturma

    protected RequestSpecification specDummy;
    //child class lardan gorebilmek ama degiştirmemek için protected yaptık. Farklı package lar olsa da extends
    //ile bu class a ulaşabiliyrm. Inheritance ile.
    String url="http://dummy.restapiexample.com/api/v1";
    @Before
    public void setUp(){

        specDummy=new RequestSpecBuilder().setBaseUri(url).build();

    }




    //variaable'lar
    protected Response actualResponse;




}
