package test;

import baseUrl.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.json.JSONObject;
import org.junit.Test;
import testData.TestDataHerOkuApp;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class C21_Post_TestDataKullanimi extends HerokuAppBaseUrl {
     /*
    https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip
    bir POST request gonderdigimizde donen response’un status kodunu ve id haric
    body'sinin asagidaki gibi oldugunu test edin.

	Request body
	      {
	      "firstname" : "Ahmet",
	      "lastname" : “Bulut",
	      "totalprice" : 500,
	      "depositpaid" : false,
	      "bookingdates" : {
                      "checkin" : "2021-06-01",
                      "checkout" : "2021-06-10"
                        },
	      "additionalneeds" : "wi-fi"
	       }


	Expected Body
	{
    "bookingid":24,
    "booking":{
            "firstname":"Ahmet",
            "lastname":"Bulut",
            "totalprice":500,
            "depositpaid":false,
            "bookingdates":{
                            "checkin":"2021-06-01",
                            "checkout":"2021-06-10"
                            },
            "additionalneeds":"wi-fi"
               }
    }
     */

    @Test
    public void test01() {
        //post request için ilgili degerleri degiştiriniz.
        String firstname = "Mesut";
        String lastname = "KOC";
        int totalprice = 350;
        boolean depositpaid = true;
        String checkin = "2020-07-10";
        String checkout = "2021-01-10";
        String additionalneeds = "internet";


        specHeroku = specHeroku.pathParam("pp1", "booking");
        TestDataHerOkuApp testDataHerOkuApp = new TestDataHerOkuApp();
        JSONObject innerRequestBody;
        innerRequestBody = testDataHerOkuApp.postInnerJsonDataOlustur(checkin, checkout);
        JSONObject anaRequestBody;

        anaRequestBody = testDataHerOkuApp.postAnaJsonDataOlustur(firstname, lastname,
                totalprice, depositpaid, innerRequestBody, additionalneeds);

        actualResponse = given().contentType(ContentType.JSON).spec(specHeroku)
                .when().body(anaRequestBody.toString()).post("/{pp1}");


        //actual Response umu oluşturdum

        //şimdi ise expected Responsumu oluşturuacgım
        JSONObject expectedResponse;
        expectedResponse = testDataHerOkuApp.expectedPostDataOlustur("", innerRequestBody, anaRequestBody);

        //donen actualResponse u karşılaştırabilmem için onu Path ile çevrelemem gerekiypr. Ki expected ile test edelim.
        JsonPath pathActualResponse = actualResponse.jsonPath();
        assertEquals(testDataHerOkuApp.basariliStatuskodu,actualResponse.statusCode());
        assertEquals(testDataHerOkuApp.basariliContentType,actualResponse.contentType());



        assertFalse(pathActualResponse.getString("bookingid").isEmpty());
        assertEquals(expectedResponse.getJSONObject("booking").get("firstname"), pathActualResponse.get("booking.firstname"));
        assertEquals(expectedResponse.getJSONObject("booking").get("lastname"), pathActualResponse.get("booking.lastname"));
        assertEquals(expectedResponse.getJSONObject("booking").get("totalprice"), pathActualResponse.get("booking.totalprice"));
        assertEquals(expectedResponse.getJSONObject("booking").get("depositpaid"), pathActualResponse.get("booking.depositpaid"));

        assertEquals(expectedResponse.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"), pathActualResponse.get("booking.bookingdates.checkin"));
        assertEquals(expectedResponse.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"), pathActualResponse.get("booking.bookingdates.checkout"));
        assertEquals(expectedResponse.getJSONObject("booking").get("additionalneeds"), pathActualResponse.get("booking.additionalneeds"));


    }
}
