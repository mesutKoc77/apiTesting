package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class C14_Put_SoftAssertIleExpectedDataTesti {
    /*
    http://dummy.restapiexample.com/api/v1/update/21 url’ine asagidaki
    body’ye sahip bir PUT request gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.

            Request Body
            {
                "status":"success",
                "data":{
                        "name":“Ahmet",
                        "salary":"1230",
                        "age":"44",
                        "id":40
                        }
            }


            Response Body
            {
            "status":"success",
            "data":{
                "status":"success",
                "data":{
                        "name":“Ahmet",
                        "salary":"1230",
                        "age":"44",
                        "id":40
                        }
                   },
            "message":"Successfully! Record has been updated."
            }
                 */

    @Test
    public void test01() {
        //restapiexample adli bir sürücü Kursum var. 21 id ye sahip olan çalışanımın bilgilerinde hata tespit ediyprum
        //ve guncellemek istiyorum.ismi Ahmet, maaşı:1230 yasş:44 id sini de 40 yapmak istiyroum ve kayıt ediyrm bu
        //bu bilgileri de. Bunu yapınca Api ilgili url ve methodu algilayarak bu bilgileri aninda gidip DataBase ime
        //kayit ediyor ve sonunda bana kayit başarılı diyerek ilgili çalışanımın yeni bilgilerini ayrıntılı bir şekilde
        //dönüyyor.
        //işte bu dönen bilgiyi çalıştıgım firma bana verecek ve bu şekilde dönmesi gerekiyor ve sen de bunu test et di-
        //yecek.

        Response response;
        String url = "http://dummy.restapiexample.com/api/v1/update/21";
        /*
         Request Body
            {
                "status":"success",
                "data":{
                        "name":“Ahmet",
                        "salary":"1230",
                        "age":"44",
                        "id":40
                        }
            }
         */

        JSONObject requestPutBody = new JSONObject();

            JSONObject requestInnerData = new JSONObject();
            requestInnerData.put("name", "Ahmet");
            requestInnerData.put("salary", "1230");
            requestInnerData.put("age", "44");
            requestInnerData.put("id", 40);

        requestPutBody.put("status", "success");
        requestPutBody.put("data", requestInnerData);


        response = given().contentType(ContentType.JSON)
                .body(requestPutBody.toString())
                .put(url);
        //tdegişiklik talebimi yolladım

        JSONObject expectedAnaResponse = new JSONObject();
           JSONObject expectedDisDataResponse = new JSONObject();
             JSONObject expectedIcDataResponse = new JSONObject();
                   expectedIcDataResponse.put("name", "Ahmet");
                   expectedIcDataResponse.put("salary", "1230");
                   expectedIcDataResponse.put("age", "44");
                   expectedIcDataResponse.put("id", 40);
           expectedDisDataResponse.put("status", "success");
           expectedDisDataResponse.put("data", expectedIcDataResponse);
        expectedAnaResponse.put("status", "success");
        expectedAnaResponse.put("data", expectedDisDataResponse);
        expectedAnaResponse.put("message", "Successfully! Record has been updated.");

        // System.out.println(expectedAnaResponse);

        /*
        {"data":
             {"data":
                {"name":"Ahmet",
                 "id":40,
                  "salary":"1230",
                  "age":"44"
                  },
             "status":"success"},
       "message":"Successfully! Record has been updated.",
       "status":"success"}
         */

        // response.prettyPrint();
        /*
           {
            "status":"success",
              "data": {
                    "data":  {
                            "name":"Ahmet",
                            "id":40,
                            "salary":"1230",
                            "age":"44"
                            },
                  "status":"success"
                    },
        "message":"Successfully! Record has been updated."

        }
         */

        //degişiklik talebimi yollamıştım ve donus yaptı bana.
        //bana donen ile benden Beklenen i şimdi karşılaştırmam gerekiyor
        //Dönen bilgilierni karşılaştırmaya musait obje haline getirmrm gerekiyor. Dolayısıyla JsonPath formatına
        //donusturmeliyim


        JsonPath responsePath = response.jsonPath();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(responsePath.get("status"), expectedAnaResponse.get("status"));//actual taraf jsonPath e donmuş Objem.
        softAssert.assertEquals(responsePath.get("data.data.name"), expectedAnaResponse.getJSONObject("data").getJSONObject("data").get("name"));//actual taraf jsonPath e donmuş Objem.
        softAssert.assertEquals(responsePath.get("data.data.id"), expectedAnaResponse.getJSONObject("data").getJSONObject("data").get("id"));
        softAssert.assertEquals(responsePath.get("data.data.salary"), expectedAnaResponse.getJSONObject("data").getJSONObject("data").get("salary"));
        softAssert.assertEquals(responsePath.get("data.data.age"), expectedAnaResponse.getJSONObject("data").getJSONObject("data").get("age"));
        softAssert.assertEquals(responsePath.get("data.status"), expectedAnaResponse.getJSONObject("data").get("status"));
        softAssert.assertEquals(responsePath.get("message"), expectedAnaResponse.get("message"));
        softAssert.assertAll();

    }

}
