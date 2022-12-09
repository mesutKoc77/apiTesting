package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C06_Post_ResponseBodyTesti {
      /*  https://jsonplaceholder.typicode.com/posts
         url’ine asagidaki body ile bir POST request gonderdigimizde
        {
        "title":"API",
        "body":"API ogrenmek ne guzel",
        "userId":10,
        }
        donen Response’un,
        status code’unun 201,
        ve content type’inin application/json
        ve Response Body'sindeki,
        "title"'in "API" oldugunu
        "userId" degerinin 100'den kucuk oldugunu
        "body" nin "API" kelimesi icerdigini
        test edin.
      */

    @Test
    public void postBodyTest() {
        Response response;
        String url;
        url = "https://jsonplaceholder.typicode.com/posts";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "API");
        jsonObject.put("body", "API ogrenmek ne guzel");
        jsonObject.put("userId", 10);

        // System.out.println("jsonObject = " + jsonObject);
        //jsonObject = {"title":"API","body":"API ogrenmek ne guzel","userId":10}
        //toString() diyerek de ilgili nesneyi yani jsonObject nesnesini String e cevirerek post ediyor
        //
        response = given().contentType(ContentType.JSON)
                .when().body(jsonObject.toString()).post(url);

        //when demek ilgili url'e benim oluşturdugum body yolla demek---yani emrimiz bu.
        //yani bunu yolladıgın ZAMAN...
        //given demek ise post un içerigini, Json olacak şekilde yolla.
        //ve
        //bunu bana en son cevap olarak VER
        //sonuc olarak given dan sonra daha çok öncesinden yapılması gereken şartlar vb. soyluyoruz.
        //when den sonra ise genellikle Aksiyon un yani ne yapılacagı emriniz veriyoruz.

        // response.prettyPrint(); // ve bana json formatinda cevap verdi
        /*
        {
    "title": "API",
    "body": "API ogrenmek ne guzel",
    "userId": 10,
    "id": 101
}
         */


        response.then().assertThat().
                statusCode(201).contentType("application/json").
                body("title", Matchers.equalTo("API")).
                body("userId", Matchers.lessThan(100)).
                body("body", Matchers.containsString("API"));

        //yukarıdaki then()'nin anlami
        //Then()	karşılaştırmak ve eşleştirecgımız koşullarımız

//1.04
    }


}
