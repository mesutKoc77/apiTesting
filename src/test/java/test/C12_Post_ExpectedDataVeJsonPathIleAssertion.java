package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C12_Post_ExpectedDataVeJsonPathIleAssertion {
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
        Response response;
        String url;

        JSONObject postEdilen = new JSONObject();
        JSONObject dateJsonObject = new JSONObject();
        dateJsonObject.put("checkin", "2021-06-01");
        dateJsonObject.put("checkout", "2021-06-10");

        postEdilen.put("firstname", "Ahmet");
        postEdilen.put("lastname", "Bulut");
        postEdilen.put("totalprice", 500);
        postEdilen.put("depositpaid", false);
        postEdilen.put("bookingdates", dateJsonObject);
        postEdilen.put("additionalneeds", "wi-fi");

        //post edilecek bilgilerin objesi oluşturuldu
        //şimdi ise beklenilen objenin oluşturulmasi gerekmektedir.
        JSONObject catiExpectedJson = new JSONObject();


        JSONObject bookingExpectedJson = new JSONObject();
        JSONObject dateExpectedJson = new JSONObject();
        dateExpectedJson.put("checkin", "2021-06-01");
        dateExpectedJson.put("checkout", "2021-06-10");

        bookingExpectedJson.put("firstname", "Ahmet");
        bookingExpectedJson.put("lastname", "Bulut");
        bookingExpectedJson.put("totalprice", 500);
        bookingExpectedJson.put("depositpaid", false);
        bookingExpectedJson.put("bookingdates", dateExpectedJson);
        bookingExpectedJson.put("additionalneeds", "wi-fi");

        catiExpectedJson.put("bookingid", 24);
        catiExpectedJson.put("booking", bookingExpectedJson);

        url = "https://restful-booker.herokuapp.com/booking";
        response = given().contentType(ContentType.JSON)
                .body(postEdilen.toString())
                .post(url);

        //şimdi Assert edecegım için dönen Response, expexted Jason objemiz ile birebir karşılaştırllabilmesi için
        //path formatına donusturulmesi gerekmektedir.

        JsonPath pathResponse = response.jsonPath();

        assertEquals("booking firstname çalışmadı",catiExpectedJson.getJSONObject("booking").get("firstname"), pathResponse.getString("booking.firstname"));
        assertEquals("hata mesaji",catiExpectedJson.getJSONObject("booking").get("lastname"), pathResponse.getString("booking.lastname"));
        assertEquals(catiExpectedJson.getJSONObject("booking").get("totalprice"), pathResponse.getInt("booking.totalprice"));
        assertEquals(catiExpectedJson.getJSONObject("booking").get("depositpaid"), pathResponse.getBoolean("booking.depositpaid"));


        assertEquals(catiExpectedJson.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"), pathResponse.getString("booking.bookingdates.checkin"));
        assertEquals(catiExpectedJson.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"), pathResponse.getString("booking.bookingdates.checkout"));

        assertEquals(catiExpectedJson.getJSONObject("booking").get("additionalneeds"), pathResponse.getString("booking.additionalneeds"));

        //Assert'ü sildim ve import static org.junit.Assert.assertEquals; olarak import ettim.
        //jsonPath de sadece "get" de kullanılabiliyor. getInt veya getString demeden ama
        //valuesi String olana getInt dyemezsin



    }
}
