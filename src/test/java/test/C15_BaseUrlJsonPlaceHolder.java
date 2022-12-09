package test;

import baseUrl.JsonPlaceHolderBaseURL;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C15_BaseUrlJsonPlaceHolder extends JsonPlaceHolderBaseURL {
    /*
    url de soru işaretinden sonra gelenler query yani talep variable inin paramterleri iken,
    base url den sonra gelenler ise path in parametresi dir..
    soru işaretinden sonra da key ve valure ler gelmektedir.
    path parametresi ise ilgili sitenin bir başka bölümü anlamı olabilir.
    yani amazon/arama vb.
    parametreleri içiçe bir yapi gibi düşünebeiliriz.
    bir resmi kurumun muhasebe yetkilisi ile görüşebilmek için
    önce güvebnlik/danışma/5.kat/101.oda
    gibi düşünebiliriz.

    query parametresi kendisinden bir onceki slash in adresinde kalir ve parametrelerini oraya yerleştirir.


     */

    @Test
    public void get01() {
        /*
        1-  https://jsonplaceholder.typicode.com/posts endpointine bir GET
         request gonderdigimizde donen response’un status code’unun 200 oldugunu
         ve Response’ta 100 kayit oldugunu test edin.
        */
        /*
        burada bse url den sonra slash ve bir kelime var yani bir parametre vererek bir adim daha ilerletmil
        dolayısyla birnci paramtre degerimiz "posts".
        ondan sonra bir slash daha olsaydı o da 2. paramteremiz olacaktı.
        slash dan oncesi de bizim base URl imiz ve onu dinamik hale getirebilmek yani bir başka url yazdıgımdaz
        dinamik olsun diye base url classında ona ait bir Base url tanımlamalıyız.

         */

        //her zaman ki gibi bize endPoint lazim
        specJsonPlace.pathParam("pp1", "posts");

        Response response;

        response = given().spec(specJsonPlace).when().get("/{pp1}");

        response.then().assertThat()
                .statusCode(200).
                body("title", hasSize(100));
        //assert that de response ın body sine ilişkin assert lerimiz varsa bunları Matchers class ından yardım  alrak
        //yaparız.
        //en ustte import etmiş olduk ve yıldız koyarak hepsini çektim


    }

    @Test
    public void get02() {
        /*
        2- https://jsonplaceholder.typicode.com/posts/44 endpointine bir GET
            request gonderdigimizde donen response’un status code’unun 200 oldugunu
            ve “title” degerinin “optio dolor molestias sit” oldugunu test edin
         */
        specJsonPlace.pathParams("pp1", "posts", "pp2", "44");

        Response response;

        response = given().spec(specJsonPlace).when().get("/{pp1}/{pp2}");
        // response.prettyPrint();

        response.then().assertThat()
                .statusCode(200).
                body("title", equalTo("optio dolor molestias sit"));


    }


    @Test
    public void delete01() {
       /*
        3- https://jsonplaceholder.typicode.com/posts/50 endpointine bir DELETE
            request gonderdigimizde donen response’un status code’unun 200 oldugunu ve
            response body’sinin null oldugunu test edin
         */
        specJsonPlace.pathParams("pp1", "posts", "pp2", "50");

        Response response;

        response = given().spec(specJsonPlace).when().delete("/{pp1}/{pp2}");
        // response.prettyPrint();

        response.then().assertThat()
                .statusCode(200)
                .body("body", nullValue());//Matchers.nullValue(); ama sildim


    }

}
