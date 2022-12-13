package testData;

import org.json.JSONObject;

import java.util.HashMap;

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

    public JSONObject expectedPostDataOlustur(Object bookingid, JSONObject postInnerJsonDataOlustur, JSONObject postAnaJsonDataOlustur) {
        JSONObject jsonData = new JSONObject();
        jsonData.put("bookingid", bookingid);
        jsonData.put("bookingdates", postInnerJsonDataOlustur);
        jsonData.put("booking", postAnaJsonDataOlustur);

        return jsonData;

    }

    //***********************//***********************//********************//************************//******************
    public String checkin;
    public String checkout;
    public String firstname;
    public String lastname;
    public double totalprice;
    public boolean depositpaid;
    public HashMap bookingdates;
    public String additionalneeds;

    public HashMap postInnerHasMapOlustur() {

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("checkin", checkin);
        hashMap.put("checkout", checkout);
        return hashMap;
    }

    public HashMap postAnaHasMapOlustur() {

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("firstname", firstname);
        hashMap.put("lastname", lastname);
        hashMap.put("totalprice", totalprice);
        hashMap.put("depositpaid", depositpaid);
        hashMap.put("bookingdates", postInnerHasMapOlustur());
        hashMap.put("additionalneeds", additionalneeds);
        return hashMap;
    }

    public HashMap expectedHashMapOlustur() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("bookingdates", postInnerHasMapOlustur());
        hashMap.put("booking", postAnaHasMapOlustur());
        return hashMap;
    }


}
