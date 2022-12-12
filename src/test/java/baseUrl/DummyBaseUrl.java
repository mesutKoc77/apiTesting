package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;


public class DummyBaseUrl {
    public static Response response;
    public static String url;

    //spec oluşturma

    protected RequestSpecification specDummy;
    //child class lardan gorebilmek ama degiştirmemek için protected yaptık. Farklı package lar olsa da extends
    //ile bu class a ulaşabiliyrm. Inheritance ile.

    @Before
    public void setUp(){
        url="http://dummy.restapiexample.com/api/v1";
        specDummy=new RequestSpecBuilder().setBaseUri(url).build();

    }




    //variaable'lar
    protected Response actualResponse;




}
