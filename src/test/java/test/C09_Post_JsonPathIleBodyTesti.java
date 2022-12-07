package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C09_Post_JsonPathIleBodyTesti {
   /*
            https://restful-booker.herokuapp.com/booking
             url’ine asagidaki body'ye sahip
            bir POST request gonderdigimizde
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
            donen Response’un,
            status code’unun 200,
            ve content type’inin application-json,
            ve response body’sindeki
                "firstname“in,"Ali",
                ve "lastname“in, "Bak",
                ve "totalprice“in,500,
                ve "depositpaid“in,false,
                ve "checkin" tarihinin 2021-06-01
                ve "checkout" tarihinin 2021-06-10
                ve "additionalneeds“in,"wi-fi"
            oldugunu test edin
     */

    @Test
    public void postJsonTest() {
        Response response;
        String url = "https://restful-booker.herokuapp.com/booking";
        JSONObject catiJsonObject = new JSONObject();
        JSONObject datesJsonObject = new JSONObject();
        datesJsonObject.put("checkin", "2021-06-01");
        datesJsonObject.put("checkout", "2021-06-10");

        catiJsonObject.put("firstname", "Ali");
        catiJsonObject.put("lastname", "Bak");
        catiJsonObject.put("totalprice", 500);
        catiJsonObject.put("depositpaid", false);
        catiJsonObject.put("bookingdates", datesJsonObject);
        catiJsonObject.put("additionalneeds", "wi-fi");


        response = given().contentType(ContentType.JSON).when().body(catiJsonObject.toString()).post(url);
        response.prettyPrint();
        /*
        {
    "bookingid": 65565,
    "booking": {
        "firstname": "Ali",
        "lastname": "Bak",
        "totalprice": 500,
        "depositpaid": false,
        "bookingdates": {
            "checkin": "2021-06-01",
            "checkout": "2021-06-10"
        },
        "additionalneeds": "wi-fi"
    }
}
         */


        response.then().assertThat().
                statusCode(200).contentType(ContentType.JSON).
                body(
                        "booking.firstname", equalTo("Ali"),
                        "booking.lastname", equalTo("Bak"),
                        "booking.totalprice", equalTo(500),
                        "booking.depositpaid", equalTo(false),
                        "booking.bookingdates.checkin", equalTo("2021-06-01"),
                        "booking.bookingdates.checkout", equalTo("2021-06-10")
                );


    }


}
