package test;

import baseUrl.DummyBaseUrl;
import io.restassured.http.ContentType;
import org.junit.Test;
import testData.TestDataDummy;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C23_Get_DeSerialization extends DummyBaseUrl {
     /*
    http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET
    request gonderdigimizde donen response’un status code’unun 200,
    content Type’inin application/json ve body’sinin asagidaki gibi
    oldugunu test edin.

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

    @Test
    public void test01() {
        //expected data
        TestDataDummy testDataDummy = new TestDataDummy();
        testDataDummy.id = 3.0;
        testDataDummy.employee_name = "Ashton Cox";
        testDataDummy.employee_salary = 86000.0;
        testDataDummy.employee_age = 66.0;
        testDataDummy.profile_image = "";
        HashMap innerHashMAp = testDataDummy.idInnerHashMapOlustur();

        testDataDummy.status = "success";
        testDataDummy.data = innerHashMAp;
        testDataDummy.message = "Successfully! Record has been fetched.";
        HashMap anaExpectedhashMap = testDataDummy.idAnaHashMapOlustur();
        System.out.println(anaExpectedhashMap);

        //once response
        specDummy.pathParams("pp1", "employee", "pp2", 3);


        response = given().contentType(ContentType.JSON).spec(specDummy)
                .when()
                .get("/{pp1}/{pp2}");
        response.prettyPrint();//bize json oalrak dondu , status code gibi verileri Json formatinda kontrol edebilirim.

        assertEquals(testDataDummy.basariliStatuskodu, response.getStatusCode());
        assertEquals(testDataDummy.basariliContentType, response.getContentType());


        //dönen response u map olarak alamlıyım ki kıyaslama gerçekleşşsin
        HashMap actualResponse = response.as(HashMap.class);
        assertEquals(anaExpectedhashMap.get("status"), actualResponse.get("status"));
        assertEquals(((Map) anaExpectedhashMap.get("data")).get("id"), ((Map) actualResponse.get("data")).get("id"));
        assertEquals(((Map) anaExpectedhashMap.get("data")).get("employee_name"), ((Map) actualResponse.get("data")).get("employee_name"));
        assertEquals(((Map) anaExpectedhashMap.get("data")).get("employee_salary"), ((Map) actualResponse.get("data")).get("employee_salary"));
        assertEquals(((Map) anaExpectedhashMap.get("data")).get("employee_age"), ((Map) actualResponse.get("data")).get("employee_age"));
        assertEquals(((Map) anaExpectedhashMap.get("data")).get("profile_image"), ((Map) actualResponse.get("data")).get("profile_image"));
        assertEquals(anaExpectedhashMap.get("message"), actualResponse.get("message"));


    }
}
