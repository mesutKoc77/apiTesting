package test;

import baseUrl.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataHerOkuApp;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C24_Post_Deserialization extends HerokuAppBaseUrl {
    /*
      https://restful-booker.herokuapp.com/booking url'ine asagidaki
      body'ye sahip bir POST request gonderdigimizde donen response'un
      id haric asagidaki gibi oldugunu test edin.

        Request body
   {
        "firstname" : "Ali",
        "lastname" : "Bak",
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
           "firstname":"Ali",
           "lastname":"Bak",
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
        //oncelikle post edilecek degerleri hazırlamalı
        TestDataHerOkuApp testDataHerOkuApp = new TestDataHerOkuApp();
        testDataHerOkuApp.checkin = "2021-06-01";
        testDataHerOkuApp.checkout = "2021-06-10";
        testDataHerOkuApp.firstname = "Ali";
        testDataHerOkuApp.lastname = "Bak";
        testDataHerOkuApp.totalprice = 500;
        testDataHerOkuApp.depositpaid = false;
        testDataHerOkuApp.bookingdates = testDataHerOkuApp.postInnerHasMapOlustur();
        testDataHerOkuApp.additionalneeds = "wi-fi";
        HashMap postValue = testDataHerOkuApp.postAnaHasMapOlustur();
        System.out.println(postValue);

        //daha sonra expectedValue yu hazırlamalı
        testDataHerOkuApp.checkin = "2021-06-01";
        testDataHerOkuApp.checkout = "2021-06-10";
        testDataHerOkuApp.firstname = "Ali";
        testDataHerOkuApp.lastname = "Bak";
        testDataHerOkuApp.totalprice = 500.0;
        testDataHerOkuApp.depositpaid = false;
        testDataHerOkuApp.bookingdates = testDataHerOkuApp.postInnerHasMapOlustur();
        testDataHerOkuApp.additionalneeds = "wi-fi";

        HashMap expectedValue = testDataHerOkuApp.expectedHashMapOlustur();
        System.out.println("expectedValue = " + expectedValue);

        //şimdi ise response hazırlanmali
        specHeroku.pathParam("pp1", "booking");
        url = "https://restful-booker.herokuapp.com/booking";
        response = given().contentType(ContentType.JSON).spec(specHeroku).body(postValue).post("{pp1}");
        response.prettyPrint();
        //donen response Json olarak dondu ama benim expexted im Map, karşılaştırma yapabilmem için
        //onu da map e cevirmem gerekiyor.

        HashMap actualResponse = response.as(HashMap.class);

        //şimdi expexted ile actual i karşılaştırabilrim.

        //oncelikle response umuz karşılıgımda ilgili kayit bir Id almış mi onu bagımsız olarak kontrol etmeliyim.

        Assert.assertNotNull("ilgili Id no Bulunamadi", actualResponse.get("bookingid"));

        assertEquals(((Map) actualResponse.get("booking")).get("firstname"), ((Map) expectedValue.get("booking")).get("firstname"));
        assertEquals(((Map) actualResponse.get("booking")).get("lastname"), ((Map) expectedValue.get("booking")).get("lastname"));
        assertEquals(((Map) actualResponse.get("booking")).get("totalprice"), ((Map) expectedValue.get("booking")).get("totalprice"));
        assertEquals(((Map) actualResponse.get("booking")).get("depositpaid"), ((Map) expectedValue.get("booking")).get("depositpaid"));
        assertEquals(((Map) ((Map) actualResponse.get("booking")).get("bookingdates")).get("checkin"), ((Map) ((Map) actualResponse.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(((Map) ((Map) actualResponse.get("booking")).get("bookingdates")).get("checkout"), ((Map) ((Map) actualResponse.get("booking")).get("bookingdates")).get("checkout"));
        assertEquals(((Map) actualResponse.get("booking")).get("additionalneeds"), ((Map) expectedValue.get("booking")).get("additionalneeds"));
        //ODEV  NEREDE PAYLAŞILDI
        //slyatta c25 ve c26 olabilir

    }
}
