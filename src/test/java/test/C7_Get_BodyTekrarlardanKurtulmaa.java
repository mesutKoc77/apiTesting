package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class C7_Get_BodyTekrarlardanKurtulmaa {
             /*
                    https://restful-booker.herokuapp.com/booking/14018 url’ine
                    bir GET request gonderdigimizde donen Response’un,
                    status code’unun 200,
                    ve content type’inin application/json; charset=utf-8,
                    ve response body’sindeki
                        "firstname“in,"Jane",
                        ve "lastname“in, "Doe",
                        ve "totalprice“in, 111,
                        ve "depositpaid“in,true,
                        ve "additionalneeds“in,"Extra pillows please"
                    oldugunu test edin
             */

    @Test
    public void get01() {
        Response response;
        String url;

        url = "https://restful-booker.herokuapp.com/booking/14018";
        response = given().contentType(ContentType.JSON).when().get(url);

        response.then().assertThat()
                .statusCode(200).contentType("application/json; charset=utf-8").
body("firstname", equalTo("Jane"),"lastname", equalTo("Doe"),
                       "totalprice", equalTo(111),
                      "depositpaid", equalTo(true),
                   "additionalneeds", equalTo("Extra pillows please"));
        //import static org.hamcrest.Matchers.*;
        //tek tek body i yazmaya gerek yok.


    }



}
