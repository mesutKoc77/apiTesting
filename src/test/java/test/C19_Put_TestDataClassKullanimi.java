package test;

import baseUrl.JsonPlaceHolderBaseURL;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import testData.TestDataJsonPlaceHolder;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C19_Put_TestDataClassKullanimi extends JsonPlaceHolderBaseURL {
     /*
    https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip bir PUT
    request yolladigimizda donen response’in
    status kodunun 200, content type’inin “application/json; charset=utf-8”,
    Connection header degerinin “keep-alive”
    ve response body’sinin asagida verilen ile ayni oldugunu test ediniz
    Request Body

        {
        "title":"Ali",
        "body":"Merhaba",
        "userId":10,
        "id":70
        }

    Expected Data

        {
        "title":"Ali",
        "body":"Merhaba",
        "userId":10,
        "id":70
        }

  */

    @Test
    public void test01() {
        Response actualResponse;
        specJsonPlace.pathParams("pp1", "posts", "pp2", 70);
        TestDataJsonPlaceHolder testDataJsonPlaceHolder = new TestDataJsonPlaceHolder();
        JSONObject putBody;
        putBody = testDataJsonPlaceHolder.idJsonDataOlustur(10, 70, "Ali", "Merhaba");

        actualResponse = given().contentType(ContentType.JSON)
                .spec(specJsonPlace).when().body(putBody.toString())
                .put("/{pp1}/{pp2}");
        actualResponse.prettyPrint();
        //Degerleri yolladik ve actualResponse umuz bize dondu

        /*

        {
    "id": 70,
    "title": "Ali",
    "body": "Merhaba",
    "userId": 10
         }
         */

        //şimdi de bekelmiş oldugumuz Data mizi Json olarak oluşturmamaız lazım. Çünkü bizden beklenilen
        //json formatinda bir obje
        JSONObject expectedData;
        expectedData = testDataJsonPlaceHolder.idJsonDataOlustur(10, 70, "Ali", "Merhaba");
        //bekeldgimiz degerleri şimdi Obje olarak gorelim
        System.out.println(expectedData);
        /*
        {
        "id":70,
        "title":"Ali",
        "body":"Merhaba",
        "userId":10
        }
         */
        /*
        halihazirdaki expextedJson objemiz ile karşılaştırma yapabilmek adina dönen response body nin
        karşılaştırmaya musait hale gelebbilmesi için onu
        path yollarına eriştirmemiz gerekiyor.
         */
        JsonPath pathActualResponse = actualResponse.jsonPath();

        assertEquals(testDataJsonPlaceHolder.basariliStatuskodu, actualResponse.getStatusCode());
        assertEquals(testDataJsonPlaceHolder.basariliContentType, actualResponse.contentType());
        assertEquals(testDataJsonPlaceHolder.basariliConnectionHeaderDegeri, actualResponse.header("Connection"));

        assertEquals(expectedData.get("id"), pathActualResponse.get("id"));
        assertEquals(expectedData.get("title"), pathActualResponse.get("title"));
        assertEquals(expectedData.get("body"), pathActualResponse.get("body"));
        assertEquals(expectedData.get("userId"), pathActualResponse.get("userId"));


    }
}
