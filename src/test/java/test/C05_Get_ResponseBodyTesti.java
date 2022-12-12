package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C05_Get_ResponseBodyTesti {
  /*
       https://jsonplaceholder.typicode.com/posts/44 url'ine bir GET request yolladigimizda
       donen Response’in
            status code'unun 200,
            ve content type'inin ContentType.JSON,
            ve response body'sinde bulunan userId'nin 5,
            ve response body'sinde bulunan title'in "optio dolor molestias sit"
            oldugunu test edin.
 */

    @Test
    public void bodyResponseTestiGet() {
        //Kullanıcı Konak Hotel adli sitemiz de rezervasyon yaptııgında post methodu ile WebService api miz digip
        //database'e bu bilgileri kaydedeer ve response olarak da bana kayıt tamamlanmıştır demesni isterim.
        //ya da WebService api ye şu soylenıyr: git bana şu id'nin tüm sutunlardaki satir bilgilerini bana
        //DATABASE imden getir.
        //e devlet in dataBase i olarak ben sadece soy ismimi degiştirmek istiyrm dersem WebApi gidecek
        //edevlet databse inden sadece soyadını gunceleyyecek ama response olarak evet degiştirdim ama
        //tum bilgileri yeniden dndurmesi gerekiyor ki hata var mi yok mu göreyim ve bilgileneyim
        Response response;
        String url;

        url = "https://jsonplaceholder.typicode.com/posts/44";
        response = given().when().get(url);

        response.then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON).
                body("userId", Matchers.equalTo(5)).
                body("title", Matchers.equalTo("optio dolor molestias sit"));


        //yukarıdaki then()'nin anlami
        //Then()	karşılaştırmak ve eşleştirecgımız koşullarımız


        // 1 - Request icin gerekli URL ve body hazirla
        // 2 - Soruda verilmisse Expected Datayi hazirla
        // 3 - Response'u kaydet
        //response.prettyPrint();
        // 4 - Assertion

    }


}
