package testData;

import org.json.JSONObject;

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
        jsonData.put("data",innerJson );
        jsonData.put("message", message);

        return jsonData;
    }





}
