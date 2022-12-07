package test;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C02_Get_ResponseBilgileriAssertion {
    /*
            https://restful-booker.herokuapp.com/booking/5079 url’ine bir GET request
            gonderdigimizde donen Response’un,
            status code’unun 200,
            ve content type’inin application/json; charset=utf-8,
            ve Server isimli Header’in degerinin Cowboy,
            ve status Line’in HTTP/1.1 200 OK
            oldugunu test edin.
             */

    String url;
    Response response;

    @Test
    public void get02() {
        // 1- Request icin Url ve Body hazirla
        url = "https://restful-booker.herokuapp.com/booking/5079";
        // 2 - Expected Datayi hazirla
        // 3 - Response'u kaydet
        response = given().when().get(url);
        // 4 - Assertion
        response.then().assertThat().statusCode(200).contentType("application/json; charset=utf-8").
                header("Server", "Cowboy").
                statusLine("HTTP/1.1 200 OK");

    }

}
