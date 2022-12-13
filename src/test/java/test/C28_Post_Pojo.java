package test;

import baseUrl.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import org.junit.Test;
import pojos.PojoHerBookingClass;
import pojos.PojoHerDatesBookingClass;
import pojos.PojoHerSonDataClass;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class C28_Post_Pojo extends HerokuAppBaseUrl {
    /*
    https://restful-booker.herokuapp.com/booking url’ine
    asagidaki body'ye sahip bir POST request gonderdigimizde
    donen response’un id disinda asagidaki gibi oldugunu test edin.

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

Response Body

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
                                        }
                        ,
                        "additionalneeds":"wi-fi"
                              }
                    }
         */

    @Test
    public void test01() {

        //oncelikle expected Data mizi oluşturulaım.

        PojoHerDatesBookingClass innerData = new PojoHerDatesBookingClass("2021-06-01", "2021-06-10");
        PojoHerBookingClass anaData = new PojoHerBookingClass("Ahmet", "Bulut", 500, false, innerData, "wi-fi");
        PojoHerSonDataClass expectedData = new PojoHerSonDataClass("", anaData);

        //şimdi ise response u oluşturmaliyiz.
        PojoHerDatesBookingClass postInnerData = new PojoHerDatesBookingClass("2021-06-01", "2021-06-10");
        PojoHerBookingClass postAnaData = new PojoHerBookingClass("Ahmet", "Bulut", 500, false, innerData, "wi-fi");

        specHeroku.pathParams("pp1", "booking");
        response = given().contentType(ContentType.JSON).spec(specHeroku)
                .when().body(postAnaData).post("/{pp1}");

        PojoHerSonDataClass actualResponse = response.as(PojoHerSonDataClass.class);
        //System.out.println(actualResponse);
        //System.out.println(expectedData);

        assertNotNull(actualResponse.getBookingid());
        assertEquals(expectedData.getBooking().getFirstname(), actualResponse.getBooking().getFirstname());
        assertEquals(expectedData.getBooking().getLastname(), actualResponse.getBooking().getLastname());
        assertEquals(expectedData.getBooking().getTotalprice(), actualResponse.getBooking().getTotalprice());
        assertEquals(expectedData.getBooking().isDepositpaid(), actualResponse.getBooking().isDepositpaid());
        assertEquals(expectedData.getBooking().getBookingdates().getCheckin(), actualResponse.getBooking().getBookingdates().getCheckin());
        assertEquals(expectedData.getBooking().getBookingdates().getCheckout(), actualResponse.getBooking().getBookingdates().getCheckout());
        assertEquals(expectedData.getBooking().getAdditionalneeds(), actualResponse.getBooking().getAdditionalneeds());


    }
}
