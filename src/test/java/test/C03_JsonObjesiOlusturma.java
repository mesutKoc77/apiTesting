package test;

import org.json.JSONObject;
import org.junit.Test;

public class C03_JsonObjesiOlusturma {
    /*
   Asagidaki JSON Objesini olusturup konsolda yazdirin.
   {
       "title":"Ahmet",
       "body":"Merhaba",
       "userId":1
   }
*/

    @Test
    public void jsonObjeOlusturma() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "Ahmet");
        jsonObject.put("body", "Merhaba");
        jsonObject.put("userId", 1);
        System.out.println(jsonObject);


    }

    @Test //nestedJsonObjeOlusuturma
    public void jsonObje2() {

         /*
                {
                 "firstname":"Jim",
                 "additionalneeds":"Breakfast",
                 "bookingdates":{
                         "checkin":"2018-01-01",
                         "checkout":"2019-01-01"
                                },
                  "totalprice":111,
                  "depositpaid":true,
                  "lastname":"Brown"
                  }
         */
        JSONObject mainJsonObject = new JSONObject(); //once bunu oluşturdum, 3. sirada iken inner i.
        JSONObject innerJsonObject = new JSONObject();

        mainJsonObject.put("firstname", "Jim");
        mainJsonObject.put("additionalneeds", "Breakfast");
        mainJsonObject.put("bookingdates", innerJsonObject);
        mainJsonObject.put("totalprice", 111);
        mainJsonObject.put("depositpaid", true);
        mainJsonObject.put("lastname", "Brown");

        innerJsonObject.put("checkin", "2018-01-01");
        innerJsonObject.put("checkout", "2019-01-01");

        System.out.println(mainJsonObject);

    }


}
