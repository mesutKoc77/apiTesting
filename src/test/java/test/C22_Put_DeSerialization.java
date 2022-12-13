package test;

import baseUrl.JsonPlaceHolderBaseURL;
import io.restassured.http.ContentType;
import org.junit.Test;
import testData.TestDataJsonPlaceHolder;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C22_Put_DeSerialization extends JsonPlaceHolderBaseURL {
    /*
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

    Expected Data :
        {
        "title":"Ahmet",
        "body":"Merhaba",
        "userId":10,
        "id":70
        }
     */
    /*
    NOTLAR
     //işlem yaptıgımız nesneyi, saklamak istedigimiz formata cevirme işlemine Serialization denir.
    //java objelerini Api sorgulaarı yapmak uzere Json objesine cevirmeye Serialization denir.
    //mesela Map leri Json objelerine cevirmeye serialization denir.

    //dönen Json objesini testlerimzde kullanmak üzere Java objesine cevirmeye (MAP) de de-serialization denir

    //mesela sorgu sonucnda bize donen Response'u Java da mevcut olan bir objeye donusturalim ki karşılaştrma ve teste
    //musait hale gelebilsin.
        /*
        mesela biz post request ederken bunu direkt Map olarak oluşturabiliri.z cunku json objesi
        java da yok fakat de serilization yaparken karşlışlatırm yapmak adına en anlamlı obje odur.
        bu durumda oluşturulan request leri direkt key value taşıyan Map lerden oluşturmalı yani Serilization
        yapmalı,(yani gecmiş derlserde Json obje si ile oluştururlan request ler artık Java objesi olan Map ile
        oluşturulabilir. yani once biz Map oluşturacagz onu alip Json objesine çevirip yollayacagz. cunku request bizden
        Json olarak talep ediliyor.)
        dönen obje de (Json olarak donuyor) direkt map olarak donmeyecegi için onu da map e dondurmeliyiz,
        bu durumda yani de-serialization
        yapıldııgnnda iki map birbiriyle karşılaştırılmış olacaktir.
        de-serialization yapaılırken kullanıclak kutuphane de Gson dir.
        Sonuc olarak
        giderken requesti Map olarak kayıt ettik, bizden json olarak istedikleri için Json yapacagız Mapi,
        donerken de onceden java mızda kayıtlı map objesşyle karşılaştırma yapmak için dönen Json i
        Map yapmalıyız.
         */

    @Test
    public void put01() {
        url = "https://jsonplaceholder.typicode.com/posts/70";
        /*
        burda class ımız JsonPlaceHolder' a extends oldugu için o classdan variable lari açğırabilirz.
         */
        //once expected Data yı oluşturmak gerekiyor. hash map şeklinde.
        specJsonPlace.pathParams("pp1", "posts", "pp2", 70);

        TestDataJsonPlaceHolder testDataJsonPlaceHolder = new TestDataJsonPlaceHolder();
        testDataJsonPlaceHolder.title = "Ahmet";
        testDataJsonPlaceHolder.body = "Merhaba";
        testDataJsonPlaceHolder.userId = 10;
        testDataJsonPlaceHolder.id = 70;
        HashMap expectedResponse = testDataJsonPlaceHolder.mapJsonOlustur();

        //şimdi ise responsumuzu oluşturmaliyiz.

        testDataJsonPlaceHolder.title = "Ahmet";
        testDataJsonPlaceHolder.body = "Merhaba";
        testDataJsonPlaceHolder.userId = 10.0;
        testDataJsonPlaceHolder.id = 70.0;
        HashMap requestJsonObject = testDataJsonPlaceHolder.mapJsonOlustur();

        response = given().contentType(ContentType.JSON).spec(specJsonPlace)
                .when().body(requestJsonObject).put("/{pp1}/{pp2}");

        //response'umuz oluştu.

        //yukarıda dikkat edilirse her iki obje de HashMap; fakat bize donen bilgi artık Json yani Javanın olmayan bir
        //obje ile donuyor. cunku response bolumunde zaten bana Json olarak don dıyorum.
        //ama karşılaştırma yapılabilmesi için donen Json'ı ben HAsHmape dondermeliyim ki
        //hashmap olan expected data ile karşılaştırabileyim.

        HashMap actualResponse = response.as(HashMap.class);


        assertEquals(expectedResponse.get("title"), actualResponse.get("title"));
        assertEquals(expectedResponse.get("body"), actualResponse.get("body"));
        assertEquals(expectedResponse.get("userId"), (actualResponse.get("userId")));
        assertEquals(expectedResponse.get("id"), ( actualResponse.get("id")));


        //bana " given().contentType(ContentType.JSON)" response'u content type i Json olarak yolla diyorum.
        //giderken spec imi al
        //request de body zaten Map yani Jaba nın bir objesi oldugundan onu String e cevrirerek yolla dememize gerek yok.
        //eskiden ise Json formatında ve Json java nın objesi olmadıgından onu string e ceviriyoruz.


    }

}
