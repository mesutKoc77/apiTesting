package test;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class C08_JSONObjectVerileriCagirma {


     /*
    {
    "firstName": "John",
    "lastName": "doe",
    "age": 26,
    "address": {
        "streetAddress": "naist street",
        "city": "Nara",
        "postalCode": "630-0192"
               },
    "phoneNumbers": [
             {
            "type": "iPhone",
            "number": "0123-4567-8888"
             },
          {
            "type": "home",
            "number": "0123-4567-8910"
          }
       ]
}
     */

    @Test
    public void jsonPath01() {

        //https://jsonpath.com/
        JSONObject catiObject = new JSONObject();
        JSONObject adressJson = new JSONObject();
        JSONObject iPhone = new JSONObject();
        JSONObject home = new JSONObject();
        iPhone.put("type", "iPhone");
        iPhone.put("number", "0123-4567-8888");
        home.put("type", "home");
        home.put("number", "0123-4567-8910");
        JSONArray phoneArrayJson = new JSONArray();
        phoneArrayJson.put(iPhone);
        phoneArrayJson.put(home);

        adressJson.put("streetAddress", "naist street");
        adressJson.put("city", "Nara");
        adressJson.put("postalCode", "630-0192");

        catiObject.put("firstName", "John");
        catiObject.put("lastName", "doe");
        catiObject.put("age", 26);
        catiObject.put("address", adressJson);
        catiObject.put("phoneNumbers", phoneArrayJson);

        System.out.println("firstName = " + catiObject.get("firstName"));
        System.out.println("catiObject.get(\"lastName\") = " + catiObject.get("lastName"));
        System.out.println("catiObject.get(\"age\") = " + catiObject.get("age"));
        System.out.println("catiObject.get(\"address\") = " + catiObject.get("address"));
        System.out.println("catiObject.get(\"phoneNumbers\") = " + catiObject.get("phoneNumbers"));
        System.out.println("phoneArrayJson.get(0) = " + phoneArrayJson.get(0));
        System.out.println("phoneArrayJson.get(1) = " + phoneArrayJson.get(1));

        System.out.println("*********************************");
        System.out.println("=============YA DA==============");

        JSONObject kisiBilgisi = new JSONObject();
        JSONObject adresBilgisi = new JSONObject();
        adresBilgisi.put("streetAddress", "naist street");
        adresBilgisi.put("city", "Nara");
        adresBilgisi.put("postalCode", "630-0192");

        JSONArray telefonBilgisi = new JSONArray();
        JSONObject sifirtelefonBilgisi = new JSONObject();
        JSONObject birtelefonBilgisi = new JSONObject();
        sifirtelefonBilgisi.put("type", "iPhone");
        sifirtelefonBilgisi.put("number", "0123-4567-8888");
        birtelefonBilgisi.put("type", "home");
        birtelefonBilgisi.put("number", "0123-4567-8910");
        telefonBilgisi.put(0, sifirtelefonBilgisi);
        telefonBilgisi.put(1, birtelefonBilgisi);
        kisiBilgisi.put("firstName", "John");
        kisiBilgisi.put("lastName", "doe");
        kisiBilgisi.put("age", 26);
        kisiBilgisi.put("address", adresBilgisi);
        kisiBilgisi.put("phoneNumbers", telefonBilgisi);

        System.out.println(kisiBilgisi);

        System.out.println("*********************************");
        System.out.println("=============YA DA==============");

        //burada ise var olan Json lar bazında Çatıyı kuracagız.
        //kaç adet json var dedigiimizde açılıp kapanan süslü parantezleri esas alacgız
        //4 adet var +1 de arrayJson var

        JSONObject kisiBilgisi1 = new JSONObject();
        JSONObject adresBilgisi1 = new JSONObject();
        JSONObject cepTelefonBilgisi1 = new JSONObject();
        JSONObject evTelefonBilgisi1 = new JSONObject();

        JSONArray tumTelefonBilgisi = new JSONArray();

        //en içten bşalayarak bilgileri giriecegim. En içte cep telefon bilgisi var

        cepTelefonBilgisi1.put("type", "iPhone");
        cepTelefonBilgisi1.put("number", "0123-4567-8888");
        evTelefonBilgisi1.put("type", "home");
        evTelefonBilgisi1.put("number", "0123-4567-8910");

        tumTelefonBilgisi.put(cepTelefonBilgisi1);
        tumTelefonBilgisi.put(evTelefonBilgisi1);

        adresBilgisi1.put("streetAddress", "naist street");
        adresBilgisi1.put("city", "Nara");
        adresBilgisi1.put("postalCode", "630-0192");

        kisiBilgisi1.put("firstName", "John");
        kisiBilgisi1.put("lastName", "doe");
        kisiBilgisi1.put("age", 26);
        kisiBilgisi1.put("address", adresBilgisi1);
        kisiBilgisi1.put("phoneNumbers", tumTelefonBilgisi);

        System.out.println(kisiBilgisi1);
        /*
        {
        "firstName":"John",
        "lastName":"doe",
        "address":
                  {"streetAddress":"naist street",
                   "city":"Nara",
                   "postalCode":"630-0192"
                   },
        "age":26,
        "phoneNumbers":
                [
                    {
                    "number":"0123-4567-8888",
                     "type":"iPhone"
                     },

                    {"number":"0123-4567-8910",
                     "type":"home"
                     }
               ]
      }
         */

        System.out.println(kisiBilgisi1.get("firstName"));//John
        System.out.println(kisiBilgisi1.get("lastName"));//doe
        System.out.println(kisiBilgisi1.get("age"));//26
        System.out.println(kisiBilgisi1.getJSONObject("address").get("streetAddress"));//naist street
        System.out.println(kisiBilgisi1.getJSONObject("address").get("city"));//Nara
        System.out.println(kisiBilgisi1.getJSONObject("address").get("postalCode"));//630-0192
        System.out.println(kisiBilgisi1.getJSONArray("phoneNumbers").getJSONObject(0).get("type"));
        System.out.println(kisiBilgisi1.getJSONArray("phoneNumbers").getJSONObject(0).get("number"));
        System.out.println(kisiBilgisi1.getJSONArray("phoneNumbers").getJSONObject(1).get("type"));
        System.out.println(kisiBilgisi1.getJSONArray("phoneNumbers").getJSONObject(1).get("number"));


    }


}
