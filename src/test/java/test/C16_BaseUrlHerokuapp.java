package test;

import baseUrl.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C16_BaseUrlHerokuapp extends HerokuAppBaseUrl {
    /*
        Class icinde 2 Test metodu olusturun ve asagidaki testleri yapin
    */

    @Test
    public void get01() {
         /*
        1-  https://restful-booker.herokuapp.com/booking endpointine bir GET
            request gonderdigimizde donen response’un status code’unun 200 oldugunu
            ve Response’ta 24389 id'sine sahip bir booking oldugunu test edin
    */
        specHeroku.pathParam("pp1", "booking");
        Response response;

        response = given().spec(specHeroku).when().get("/{pp1}");

        response.then().assertThat()
                .statusCode(200)
                .body("bookingid", Matchers.hasItem(11406));
        //burda Matchers i silip ilgili işlemleri de yapabilrisin, import ederek


    }


    @Test
    public void post01() {

    /*
        2- https://restful-booker.herokuapp.com/booking endpointine asagidaki
         body’ye sahip bir POST request gonderdigimizde donen response’un status
         code’unun 200 oldugunu ve “firstname” degerinin “Ali” oldugunu test edin
      {
      "firstname" : "Ali",
      "lastname" : “Bak",
      "totalprice" : 500,
      "depositpaid" : false,
      "bookingdates" : {
                    "checkin" : "2021-06-01",
                    "checkout" : "2021-06-10"
                    },
      "additionalneeds" : "wi-fi"
       }
      */
        specHeroku.pathParam("pp1", "booking");
        Response response;

        JSONObject jsonPostBody = new JSONObject();
        JSONObject innerPostBody = new JSONObject();
        innerPostBody.put("checkin", "2021-06-01");
        innerPostBody.put("checkout", "2021-06-10");

        jsonPostBody.put("firstname", "Ali");
        jsonPostBody.put("lastname", "Bak");
        jsonPostBody.put("totalprice", 500);
        jsonPostBody.put("depositpaid", false);
        jsonPostBody.put("bookingdates", innerPostBody);
        jsonPostBody.put("additionalneeds", "wi-fi");


        response = given()
                .contentType(ContentType.JSON)
                .spec(specHeroku)
                .when()
                .body(jsonPostBody.toString())
                .post("/{pp1}");
        response.prettyPrint();
        //dönen response u test edecegım için onu path ile test edebilirim. ve onun body sinin içerisine girmeliyim.
        response.then()
                .statusCode(200)
                .body("booking.firstname", Matchers.equalTo("Ali"));


    }

}
