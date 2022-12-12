package test;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C02_Get_ResponseBilgileriAssertion {
    /*
            https://restful-booker.herokuapp.com/booking/21 url’ine bir GET request
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

        url = "https://restful-booker.herokuapp.com/booking/21";
        response = given().when().get(url);
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                 .header("Server", "Cowboy").
                statusLine("HTTP/1.1 200 OK");

    }

    @Test
    public void test03(){
        //https://restful-booker.herokuapp.com/booking/13
        //buna bir get dedgimizde
        /*
          status code’unun 200,
            ve content type’inin application/json; charset=utf-8,
            ve Server isimli Header’in degerinin Cowboy,
            ve status Line’in HTTP/1.1 200 OK
            oldugunu test edin.
         */
        String url2="https://restful-booker.herokuapp.com/booking/13";
        Response response2;
        response2=given()
                .when()
                .get(url2);
        response2.prettyPrint();

        //test ediyoroum donen response2 yi

        response2.then().assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .headers("Server","Cowboy")
                .statusLine("HTTP/1.1 200 OK");


    }

}
