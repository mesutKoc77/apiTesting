package test;

import baseUrl.JsonPlaceHolderBaseURL;
import io.restassured.http.ContentType;
import org.junit.Test;
import pojos.PojoJsonPlace;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C27_Put_PojoClass extends JsonPlaceHolderBaseURL {
       /*
    C27_Put_PojoClass
 https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki
 body’e sahip bir PUT request yolladigimizda donen response’in
 response body’sinin asagida verilen ile ayni oldugunu test ediniz

     Request Body
    {
    "title":"Ahmet",
    "body":"Merhaba",
    "userId":10,
    "id":70
    }

    Expected Body
    {
    "title":"Ahmet",
    "body":"Merhaba",
    "userId":10,
    "id":70
    }

     */

    @Test
    public void test01() {
        //oncelikle expected Data hazırlanır
        PojoJsonPlace expectedValue = new PojoJsonPlace("Ahmet", "Merhaba", 10, 70);

        //burada şunu soyledik git PojoJsonPlace classina (eşitligin solu)
        //oradaki paramtreli constructor a bu bilgileri yerleştir ve ona bagli uretimi gerçekleştir.
        //orada paramtersezi constructor i silmedigimden buradan o class daki her seye erişe de bilirim.
        //to String methodu da ilgili variable lari tek tek yazıp sout etmek yerine toString ile hemen
        //tamamini tek bir toş ile yazdiracagz.
        // System.out.println(pojoJsonPlace.toString());
        //28.59
        //yukarıda paramtreli constructor yardımıyla ilgili private olarak atanmış variable lari atadım.
        //şimdi artık onları degiştiremem cunku private idi. yani obje oluştursam dahi o variable Set et desem
        //dahi private oldujları için degişiklik yapamicam.
        //bunu yapabilmem için onu gidip private olmaktan kurtarmam gerekiyor.

        //şimdi bu degerleri atadım ve onları alarak pojoJsonPlace class ındaki toString methodunun
        // içersine sokarak onları String'e çevirdi. eger o degişkenleeri alıp String e cevirmez ise bu durumda
        //bana referans yazdırır.

        //***********************
        //PojoJsonPlace class ından bir obje oluşturdum ve constructor olarak parametreli construcrtor i kullandım.
        //bu obje min ismi de expectedValue, yani bunun atandıgı her deger bu class in degerinde olacak
        //PojoJsonPlace expectedValue;

        //=================================================
        //şimdi ise response umuzu oluşturacgız.

        PojoJsonPlace responseValue = new PojoJsonPlace("Ahmet", "Merhaba", 10, 70);
        specJsonPlace.pathParams("pp1", "posts", "pp2", 70);
        response = given().contentType(ContentType.JSON).spec(specJsonPlace)
                .when().body(responseValue).put("/{pp1}/{pp2}");

        //response döndü ama bizim expected imizin türü PojoJsonPlace oldugundan donen Json body i de PojoJsonPlace
        //şekline dondurmem gerekiyor.

        PojoJsonPlace actualResponse = response.as(PojoJsonPlace.class); //tüm degerleri ve şekil şartlarını
        // o class in şekline bürüdü ve bana döndürdü.

        assertEquals(expectedValue.getBody(), actualResponse.getBody());
        assertEquals(expectedValue.getTitle(), actualResponse.getTitle());
        assertEquals(expectedValue.getId(), actualResponse.getId());
        assertEquals(expectedValue.getUserId(), actualResponse.getUserId());

        response.prettyPrint();
        System.out.println(expectedValue);
        System.out.println(actualResponse);


    }

}
