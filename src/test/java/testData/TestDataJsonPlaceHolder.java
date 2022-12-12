package testData;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class TestDataJsonPlaceHolder {



   public int basariliStatuskodu=200;
   public String basariliContentType="application/json; charset=utf-8";
   public String basariliConnectionHeaderDegeri ="keep-alive";

   //api dokumamnına gittik ve Get methodu  expectedi ve response'u için gerekli variable larin
    // neler oldugunu gorduk

    /*

        "userId"
        "id"
        "title"
        "body"
   bu variable lar hep sabit kalacaktir.

     */

        //jsonplaceholder in "put" methodunda, urline paramtre olarak girilen id ile kullanılcak body deki id nin eşit olmasi
    //gereklidir.
    //mesela post methodundan id yi hiç girmiyorz.
    public JSONObject idJsonDataOlustur(int userId, int Id, String title, String body){

        JSONObject jsonData=new JSONObject();
        jsonData.put("userId",userId);
        jsonData.put("id",Id);
        jsonData.put("title",title);
        jsonData.put("body",body);

        return jsonData;
    }

     //zDeneme class inda bunu denedim.
    //aşağıdaki şekilde de bir Json obje oluşturulup dinamik hale geitrilebilir.

    public double userId;
    public double id;
    public String title;
    public String body;

    public HashMap mapJsonOlustur() {
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("userId", userId);
        hashMap.put("id", id);
        hashMap.put("title", title);
        hashMap.put("body", body);
        return hashMap;
    }

    public JSONObject denemeIdJsonDataOlustur(){

        JSONObject jsonData=new JSONObject();
        jsonData.put("userId", userId);
        jsonData.put("id", id);
        jsonData.put("title", title);
        jsonData.put("body", body);

        return jsonData;
    }














    //diger deneme
    static String [] kullaniciIsimleri={"mesut","delil","sabri"};
    //list ile de yapılabilirdi.?
    static int [] kullaniciIdleri={11,15,20};
    static String [] kullaniciCerceveleri={"van adana","şırnak","adiyaman"};

    public static Response response;
    public static String url;

    public static void denemeIdJsonDataOlusturu() {
        url="https://jsonplaceholder.typicode.com/posts";
        int postedilen=0;
        JSONObject jsonData = new JSONObject();
        for (int i = 0; i < kullaniciCerceveleri.length; i++) {

            jsonData.put("userId", kullaniciIsimleri[i]);
            jsonData.put("title", kullaniciIdleri[i]);
            jsonData.put("body", kullaniciCerceveleri[i]);
            postedilen++;
           // System.out.println("post Edilen"+postedilen+jsonData);
            response=given().contentType(ContentType.JSON).when().body(jsonData.toString()).post(url);
            response.prettyPrint();
            response.then().assertThat().statusCode(201);
        }
        //bu post body nin actual response ile nasıl assert edilebilecegıne kadar goturlebilir.


    }



}
