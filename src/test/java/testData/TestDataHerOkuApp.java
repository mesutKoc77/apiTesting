package testData;

import org.json.JSONObject;

public class TestDataHerOkuApp {


    public int basariliStatuskodu = 200;
    public String basariliContentType = "application/json; charset=utf-8";

    public JSONObject postInnerJsonDataOlustur(String checkin, String checkout) {

        JSONObject jsonData = new JSONObject();
        jsonData.put("checkin", checkin);
        jsonData.put("checkout", checkout);
        return jsonData;
    }

    public JSONObject postAnaJsonDataOlustur(String firstname,
                                             String lastname,
                                             int totalprice,
                                             boolean depositpaid,
                                             JSONObject bookingdates, String additionalneeds) {

        JSONObject jsonData = new JSONObject();
        jsonData.put("firstname", firstname);
        jsonData.put("lastname", lastname);
        jsonData.put("totalprice", totalprice);
        jsonData.put("depositpaid", depositpaid);
        jsonData.put("bookingdates", bookingdates);
        jsonData.put("additionalneeds", additionalneeds);
        return jsonData;
    }

    public JSONObject expectedPostDataOlustur(Object bookingid,JSONObject postInnerJsonDataOlustur, JSONObject postAnaJsonDataOlustur ) {
        JSONObject jsonData = new JSONObject();
        jsonData.put("bookingid",bookingid);
        jsonData.put("bookingdates",postInnerJsonDataOlustur);
        jsonData.put("booking",postAnaJsonDataOlustur);

        return jsonData;

    }


}
