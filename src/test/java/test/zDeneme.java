package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import testData.TestDataJsonPlaceHolder;

import static io.restassured.RestAssured.given;

public class zDeneme {
    /*
    ben bir post reqest te bulunup response gormek istiyorum.
    ama surekli olarak bunu degiştirecegım

     */

    @Test
    public void test01(){
        Response response;
        String url="https://jsonplaceholder.typicode.com/posts";
        //bir başka class daki variable veya methodu public yapmaz isen obje oluştursan dahi goremezsin.
        //public yaptıktan sonra o variable veya methodu çağırabilrisin
        //public olmazsa tabii ki ayni class içiersindeki üyeler tarafından cagrıılıp gorulebilir.
        TestDataJsonPlaceHolder testDataJsonPlaceHolder=new TestDataJsonPlaceHolder();
        testDataJsonPlaceHolder.userId =300;
        testDataJsonPlaceHolder.title ="ahmet";
        testDataJsonPlaceHolder.body ="mehmet";
        testDataJsonPlaceHolder.denemeIdJsonDataOlustur();

        response=given().contentType(ContentType.JSON).when().body(testDataJsonPlaceHolder.denemeIdJsonDataOlustur().toString()).post(url);
        response.prettyPrint();
        response.then().assertThat().statusCode(201)
                .body("title", Matchers.equalTo("ahmet"));
        /*
        {
    "title": "mesut",
    "body": "koc",
    "userId": 100,
    "id": 101
       }
         */






    }


}
