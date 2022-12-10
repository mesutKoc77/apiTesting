package testData;

import org.json.JSONObject;

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


    public JSONObject idJsonDataOlustur(int userId, int Id, String title, String body){

        JSONObject jsonData=new JSONObject();
        jsonData.put("userId",userId);
        jsonData.put("id",Id);
        jsonData.put("title",title);
        jsonData.put("body",body);

        return jsonData;
    }







}
