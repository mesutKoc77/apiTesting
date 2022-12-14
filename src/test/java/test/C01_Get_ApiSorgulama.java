package test;

import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C01_Get_ApiSorgulama {
    /*
        https://restful-booker.herokuapp.com/booking/342 url’ine bir GET request gonderdigimizde donen
        Response’un,
        status code’unun 200,
        ve content type’inin application/json; charset=utf-8,
        ve Server isimli Header’in degerinin Cowboy,
        ve status Line’in HTTP/1.1 200 OK
        ve response suresinin 5 sn’den kisa oldugunu manuel olarak test ediniz.
  */
    //ilgili Api testsitesinde id silinmiş olabilir, yeni id ile heaser bolumundan biilgiler çekilip test
    //yapılabiir.

    @Test
    public void get01() {
        //not  nobetci eczane web sitemiz'in api'sini  üyemize kullanıma açtık, üyemiz  id ile arama yapipi
        // ilgili eczanenin bilgilerini istedi, api gidip bu bilgileri database den getirip üyemize cevap yani
        // response olarak donecek, Normalde ona donecek bilgi ile beklenilen bilginin eşit olması gerekiyor.


        //1- Request için once Url yani endpoint daha sonra ise Body hazırlanır
        String url = "https://restful-booker.herokuapp.com/booking/181";
        //2- expected Data Hazilanir. Burda get kullandııgmızdan bekledigimiz bir sey yok
        //3- Response u yani bize gelecek olan bilgiyi kaydetmeliyiz

        Response bizeDonecekBilgi; //obje yi oluşturduk


        bizeDonecekBilgi = given().contentType(ContentType.JSON).when().get(url);
                //bu obje'nin de value sini kayıt ettik
        //git benim endPoint imdeki degeri getir ve onu kaydet dedik.

        bizeDonecekBilgi.prettyPrint();

        /*
        {
    "firstname": "Dan",
    "lastname": "Koss",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
         */


        System.out.println("StatusCode= " + bizeDonecekBilgi.getStatusCode());
        System.out.println("contentType = " + bizeDonecekBilgi.contentType());
        Headers headers1 = bizeDonecekBilgi.getHeaders();//ctrl+alt+v ile direkt vairABLE İNİ getirebilrisin

        Headers headers = bizeDonecekBilgi.getHeaders();
        System.out.println(headers.getValue("X-Powered-By"));
        System.out.println( headers.get("Server"));
        System.out.println("StatusLine() = " + bizeDonecekBilgi.getStatusLine());
        System.out.println(".time() = " + bizeDonecekBilgi.time());

        //4 Assertion




    }


}
