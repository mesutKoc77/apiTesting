package test;

import baseUrl.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.json.JSONObject;
import org.junit.Test;
import testData.TestDataDummy;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C20_Get_TestDataKullanimi extends DummyBaseUrl {
    /*
    http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
    gonderdigimizde donen response’un status code’unun 200,
    content Type’inin application/json ve body’sinin asagidaki gibi oldugunu test edin.

	Expected Body
    {
    "status":"success",
    "data": {
            "id": 3,
            "employee_name":"Ashton Cox",
            "employee_salary":86000,
            "employee_age":66,
            "profile_image":""
            },
    "message":"Successfully! Record has been fetched."
    }
     */

    @Test
    public void test01() {
        int bilgisiTalepEdilen=3;
        specDummy.pathParams("pp1", "employee", "pp2", bilgisiTalepEdilen);
        actualResponse = given().contentType(ContentType.JSON).spec(specDummy).when()
                .get("/{pp1}/{pp2}");
        actualResponse.prettyPrint();
        /*
        {
    "status": "success",
    "data": {
        "id": 3,
        "employee_name": "Ashton Cox",
        "employee_salary": 86000,
        "employee_age": 66,
        "profile_image": ""
    },
    "message": "Successfully! Record has been fetched."
}
         */

        //actualResponse oluştu
        //şimdi biizm bekledigimiz deger nedir?
        TestDataDummy testDataDummy = new TestDataDummy();
        JSONObject expectedInnerData;
        expectedInnerData = testDataDummy.idInnerJsonDataOlustur(3, "Ashton Cox", 86000, 66, "");
        JSONObject expectedAnaData;
        expectedAnaData = testDataDummy.idAnaJsonDataOlustur("success", expectedInnerData, "Successfully! Record has been fetched.");
        System.out.println(expectedAnaData);

        /*
        {
         "status":"success",
        "data":
            {"profile_image":"","employee_name":"Ashton Cox","employee_salary":86000,"id":3,"employee_age":66
             },

        "message":"Successfully! Record has been fetched.",
       }
         */

        //bizim actual Response'u beklenilen Obje ile karşlışaltırabilmemiz için onu JsonPath ile özelliklendirmeliyiz.
        JsonPath pathActualResponse = actualResponse.jsonPath();

        assertEquals(testDataDummy.basariliStatuskodu, actualResponse.statusCode());
        assertEquals(testDataDummy.basariliContentType, actualResponse.getContentType());

        assertEquals(expectedAnaData.get("status"), pathActualResponse.get("status"));
        assertEquals(expectedAnaData.getJSONObject("data").get("profile_image"), pathActualResponse.get("data.profile_image"));
        assertEquals(expectedAnaData.getJSONObject("data").get("employee_name"), pathActualResponse.get("data.employee_name"));
        assertEquals(expectedAnaData.getJSONObject("data").get("employee_salary"), pathActualResponse.get("data.employee_salary"));
        assertEquals(expectedAnaData.getJSONObject("data").get("id"), pathActualResponse.get("data.id"));
        assertEquals(expectedAnaData.getJSONObject("data").get("employee_age"), pathActualResponse.get("data.employee_age"));
        assertEquals(expectedAnaData.get("message"), pathActualResponse.get("message"));

        //mesela ben sadece doonen repsonse un bpdy sine yonelik bilgileri test eedeceksem
        //actual Response u da path yapmama gerek kalmayacak zira expexted Data yok.

     //   actualResponse.then().assertThat()..... diyerek body nin içindekiler veye statusCode gibi
     //body e ilişkin bilgiler test edilir.




    }
}
