package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C11_Get_ExpectedDataOlusturma {
    /*
  https://jsonplaceholder.typicode.com/posts/22 url'ine
  bir GET request yolladigimizda donen response body’sinin
  asagida verilen ile ayni oldugunu test ediniz
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
        Response response;
        String url;

        url = "https://jsonplaceholder.typicode.com/posts/22";

        response = given().contentType(ContentType.JSON).when().get(url);
       // response.prettyPrint();
        JsonPath actualResponse = response.jsonPath();
      //  actualResponse.prettyPrint();
        JSONObject expectedResponse = new JSONObject();
        expectedResponse.put("userId", 3);
        expectedResponse.put("id", 22);
        expectedResponse.put("title", "dolor sint quo a velit explicabo quia nam");
        expectedResponse.put("body", "eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse");

        Assert.assertEquals(expectedResponse.get("userId"), actualResponse.getInt("userId"));
        Assert.assertEquals(expectedResponse.get("id"), actualResponse.getInt("id"));
        Assert.assertEquals(expectedResponse.get("title"), actualResponse.getString("title"));
        Assert.assertEquals(expectedResponse.get("body"), actualResponse.getString("body"));




    }
}
