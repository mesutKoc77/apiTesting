package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C04_Put_ResponseBilgileriAssertion {
      /*
        https://jsonplaceholder.typicode.com/posts/35 url’ine asagidaki
        Json formatindaki body ile bir PUT request gonderdigimizde
                {
                "title":"Ahmet",
                "body":"Merhaba",
                "userId":10,
                "id":70
                }
        donen Response’un,
            status code’unun 200,
            ve content type’inin application/json; charset=utf-8,
            ve Server isimli Header’in degerinin cloudflare,
            ve status Line’in HTTP/1.1 200 OK
      */


    @Test
    public void putIleGuncelleme() {
        //bana once response gerekiyor ki ben ondaki variable valuelerini test edeyim.
        Response response;
        //burada once response un çatısını kur
        String url = "https://jsonplaceholder.typicode.com/posts/35";
        JSONObject jsonObjectBody = new JSONObject();
        jsonObjectBody.put("title", "Kenan");
        jsonObjectBody.put("body", "sezer");
        jsonObjectBody.put("userId", "12");
        jsonObjectBody.put("id", 35);

        response = given().contentType(ContentType.JSON).
                   when().body(jsonObjectBody.toString()).put(url);

        //when demek ilgili url'e benim oluşturdugum body'i yolla veya put demek---yani emrimiz bu.
        //yani bunu yolladıgın ZAMAN
        //given demek ise yollamış oldugum bu body vb.'nin sonucunda ise bana içerigi Json olacak şekilde yolla
        //demek.
        //bunu bana en son cevap olarak VER

        response.prettyPrint();//evet burada jsonformatinda bize dondu.
        /*
                   {
                   "id": 35,
                   "title": "Kenan",
                   "body": "sezer",
                   "userId": "12"
                   }
         */


        //bana burada url lazım yani hedef olarak nereyei degiştireyim.
        //daha sonra bir Json formatinda Body lazim


        //şimdi de response u test edelim

        response.then().assertThat().
                statusCode(200).
                contentType("application/json; charset=utf-8").
                header("Server", "cloudflare").statusLine("HTTP/1.1 200 OK");

    /*
    hoCA NIN notu;
    SİRALAMA
     // 1 - Request icin Url ve Body hazirla
     // 2 - Expected Data hazirla
     // 3 - Response kaydet
     // 4 - Assertion
        //given()'dan sonra preConditionlar when() dan sonra ise yapılacak işlemler gelir.
     */



    }
}
