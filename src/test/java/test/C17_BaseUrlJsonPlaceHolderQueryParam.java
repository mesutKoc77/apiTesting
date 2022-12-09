package test;

import baseUrl.JsonPlaceHolderBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

public class C17_BaseUrlJsonPlaceHolderQueryParam extends JsonPlaceHolderBaseURL {
    // Class icinde 3 Test metodu olusturun ve asagidaki testleri yapin
    @Test
    public void get01() {
    /*
        1-  https://jsonplaceholder.typicode.com/posts endpointine bir GET
        request gonderdigimizde donen response’un status code’unun 200 oldugunu
        ve Response’ta title'in degerlerinden birisinin
        <<sunt aut facere repellat provident occaecati excepturi optio reprehenderit<<
        oldugunu test edin
     */
        specJsonPlace.pathParam("pp1", "posts");


        Response response;
        response = given().contentType(ContentType.JSON)
                .spec(specJsonPlace).when().get("/{pp1}");


        response.then().assertThat()
                .statusCode(200)
                .body("title", hasItem("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));


    }


    @Test
    public void get02() {
    /*
        2- https://jsonplaceholder.typicode.com/posts endpointine gerekli
        Query parametrelerini yazarak “title” degeri “nesciunt iure omnis dolorem tempora et accusantium”
        olan bir obje
        oldugunu test edecek bir GET request gonderdigimizde, donen response’un
        status code’unun 200 oldugunu ve "title"'in “nesciunt iure omnis dolorem tempora et accusantium” degerine sahip
         oldugunu test edin
    */

        //query parametresi talep ettigim yani ilgili sayfa fakat belirli özellikleri haiz olan bilgilieri bana döndür
        //demmektir. yani firstnaem=eric dedigimizde tüm firstname ler degil sadece ismi eric olanının id sini bana
        //getir diyprum.
        //path paramteresi ise gidilecek sayfaın yolu dur.


        Response response;

        specJsonPlace.pathParam("pp1", "posts").queryParam("title",
                "nesciunt iure omnis dolorem tempora et accusantium");
        //specHeroku nasış yazdırılanbilir.
        response = given().contentType(ContentType.JSON).spec(specJsonPlace).when().get("/{pp1}");

        response.then().assertThat()
                .statusCode(200)
                .body("title", hasItem("nesciunt iure omnis dolorem tempora et accusantium"));
        //burda equal to kodunu kullnadıgımda hata veriyor.


    }

    @Test
    public void get03() {

    /*
        3- https://jsonplaceholder.typicode.com/posts endpointine gerekli Query
         parametrelerini yazarak “id” degeri “25” ve “title” degeri
         “rem alias distinctio quo quis” olan bir objenin oldugunu test edecek bir GET request gonderdigimizde,
         donen response’un status code’unun 200 oldugunu ve “yukarıda yazılan degerlere eş” bir objenin
          oldugunu test edin.
    */

        Response response;
        specJsonPlace.pathParam("pp1", "posts").
                queryParam("id", 25);
        System.out.println(specJsonPlace);

        response = given().contentType(ContentType.JSON).spec(specJsonPlace).when().get("/{pp1}");
        response.then().assertThat()
                .statusCode(200)
                .body("id", hasItem(25),
                        "title", hasItem("rem alias distinctio quo quis")
                );


    }
}
