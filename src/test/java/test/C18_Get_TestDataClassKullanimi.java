package test;

import baseUrl.JsonPlaceHolderBaseURL;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import testData.TestDataJsonPlaceHolder;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C18_Get_TestDataClassKullanimi extends JsonPlaceHolderBaseURL {

    /*
   C18_Get_TestDataClassKullanimi
https://jsonplaceholder.typicode.com/posts/22 url'ine bir GET
 request yolladigimizda donen response’in status kodunun 200 ve
 response body’sinin asagida verilen ile ayni oldugunu test ediniz


  Response body :
   {
   "userId":3,
   "id":22,
   "title":"dolor sint quo a velit explicabo quia nam",
   "body":"eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
   um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
   }

    */
    @Test
    public void test01() {

        //once actual response u al
        specJsonPlace.pathParams("pp1", "posts", "pp2", 13);

        Response actualResponse;
        actualResponse = given().contentType(ContentType.JSON).spec(specJsonPlace).when().get("/{pp1}/{pp2}");
        actualResponse.prettyPrint();

        /*
        {
    "userId": 2,
    "id": 13,
    "title": "dolorum ut in voluptas mollitia et saepe quo animi",
    "body": "aut dicta possimus sint mollitia voluptas commodi quo doloremque\niste corrupti reiciendis voluptatem eius rerum\nsit cumque quod eligendi laborum minima\nperferendis recusandae assumenda consectetur porro architecto ipsum ipsam"
}
         */

        //daha sonra expected olan ile karşılaştır, karşılaştırma yapabilmen için de onu Json Object
        //e çevirmen gerekiyor.
        JSONObject expectedResponse;

        TestDataJsonPlaceHolder testDataJsonPlaceHolder = new TestDataJsonPlaceHolder();

        expectedResponse = testDataJsonPlaceHolder.idJsonDataOlustur(2, 13, "dolorum ut in voluptas mollitia et saepe quo animi",
                "aut dicta possimus sint mollitia voluptas commodi quo doloremque\niste corrupti reiciendis voluptatem eius rerum\nsit cumque quod eligendi laborum minima\nperferendis recusandae assumenda consectetur porro architecto ipsum ipsam");


        //İKİ DATA DA OLUŞTU VE ŞMDİ BUNLARI KARŞILAİTIRACAGIZ
        //DONEN RESPONSE U KARŞILAŞTIRMAK İÇİN ONCE ONU PATH'E CAST ETMELİYİM

        JsonPath pathActualResponse = actualResponse.jsonPath();

        //assert leri junıt tten alıyoruz.

        assertEquals(testDataJsonPlaceHolder.basariliStatuskodu, actualResponse.getStatusCode());
        assertEquals(expectedResponse.get("userId"), pathActualResponse.get("userId"));
        assertEquals(expectedResponse.get("id"), pathActualResponse.get("id"));
        assertEquals(expectedResponse.get("title"), pathActualResponse.get("title"));
        assertEquals(expectedResponse.get("body"), pathActualResponse.get("body"));


    }

}
