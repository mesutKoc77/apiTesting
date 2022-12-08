package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C13_Get_SoftAssertIleExpectedDataTesti {
       /*
    http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
    gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.
Response Body
        {
        "status":"success",
        "data":{
                "id":3,
                "employee_name":"Ashton Cox",
                "employee_salary":86000,
                "employee_age":66,
                "profile_image":""
                },
        "message":"Successfully! Record has been fetched."
        }
     */
    //notlar
    //Kullanıcı Konak Hotel adli sitemiz de rezervasyon yaptııgında post methodu ile WebService api miz digip
    //database'e bu bilgileri kaydedeer ve response olarak da bana kayıt tamamlanmıştır demesni isterim.

    @Test
    public void test01() {
        Response response;
        String url;
        //Get request imiz Json formatinda
        //once en içteki Json lardan başlanmali...
        JSONObject responseExpectedCatiObject = new JSONObject();
        JSONObject responseExpectedNestedDataObject = new JSONObject();

        responseExpectedNestedDataObject.put("id", 3);
        responseExpectedNestedDataObject.put("employee_name", "Ashton Cox");
        responseExpectedNestedDataObject.put("employee_salary", 86000);
        responseExpectedNestedDataObject.put("employee_age", 66);
        responseExpectedNestedDataObject.put("profile_image", "");

        responseExpectedCatiObject.put("status", "success");
        responseExpectedCatiObject.put("data", responseExpectedNestedDataObject);
        responseExpectedCatiObject.put("message", "Successfully! Record has been fetched.");

    /*
    {
    "status":"success",
    "data":
         {
         "profile_image":"",
         "employee_name":"Ashton Cox",
         "employee_salary":86000,
         "id":3,
          "employee_age":66
          },
    "message":"Successfully! Record has been fetched."

    }
     */

        url = "http://dummy.restapiexample.com/api/v1/employee/3";
        response = given().contentType(ContentType.JSON).when().get(url);
        //burada ilgili Api'ye gitti, talebini iletti, Api de dataBase den bu bilgileri çekip Api'ye iletti ve Api
        //client yani talep eden kişi ye bunu gösterdi.
       response.prettyPrint();

        //actual yani asil donen Response u, beklenen Response ile karşilaştırabilmem için once onu jsonpath i
        //algilayacak formata çevirmem gerekiyor.
        //belki "employee_name"'in value sini gitti employee_salary'e yapıştırdı
        //ya da id'ye hiçbir deger atamadi.

        JsonPath responseJsonPath = response.jsonPath();

        SoftAssert softAssert = new SoftAssert();

        assertEquals(responseJsonPath.get("status"), responseExpectedCatiObject.get("status"));
        assertEquals(responseJsonPath.get("data.profile_image"), responseExpectedCatiObject.getJSONObject("data").get("profile_image"));
        assertEquals(responseJsonPath.get("data.employee_name"), responseExpectedCatiObject.getJSONObject("data").get("employee_name"));
        assertEquals(responseJsonPath.get("data.employee_salary"), responseExpectedCatiObject.getJSONObject("data").get("employee_salary"));
        assertEquals(responseJsonPath.get("data.id"), responseExpectedCatiObject.getJSONObject("data").get("id"));
        assertEquals(responseJsonPath.get("data.employee_age"), responseExpectedCatiObject.getJSONObject("data").get("employee_age"));
        assertEquals(responseJsonPath.get("message"), responseExpectedCatiObject.get("message"));
        softAssert.assertAll();


    }


}
