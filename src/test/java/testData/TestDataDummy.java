package testData;

import org.json.JSONObject;

import java.util.HashMap;

public class TestDataDummy {

    public int basariliStatuskodu = 200;
    public String basariliContentType = "application/json";


    public JSONObject idInnerJsonDataOlustur(int id, String employee_name,
                                             int employee_salary, int employee_age,
                                             String profile_image) {

        JSONObject jsonData = new JSONObject();
        jsonData.put("id", id);
        jsonData.put("employee_name", employee_name);
        jsonData.put("employee_salary", employee_salary);
        jsonData.put("employee_age", employee_age);
        jsonData.put("profile_image", profile_image);
        return jsonData;
    }


    public JSONObject idAnaJsonDataOlustur(String status, JSONObject innerJson, String message) {

        JSONObject jsonData = new JSONObject();
        jsonData.put("status", status);
        jsonData.put("data", innerJson);
        jsonData.put("message", message);

        return jsonData;
    }

    //diger yontem bu aşağodaki yontem hem Json hem de Map e uyarlnabilir.
    //burada variable ları oluşturarak devam edecegız.

    public String employee_name;
    public double employee_salary;
    public double employee_age;
    public String profile_image;
    public double id;
    public String status;
    public String message;
    public HashMap data;

    public HashMap idInnerHashMapOlustur() {

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", id);
        hashMap.put("employee_name", employee_name);
        hashMap.put("employee_salary", employee_salary);
        hashMap.put("employee_age", employee_age);
        hashMap.put("profile_image", profile_image);
        return hashMap;
    }

    public HashMap idAnaHashMapOlustur() {

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", status);
        hashMap.put("data", idInnerHashMapOlustur());
        hashMap.put("message", message);

        return hashMap;
    }


}
