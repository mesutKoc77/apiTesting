package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Before;

public class HerokuAppBaseUrl {
    public static Response response;
    public static String url;
    protected RequestSpecification specHeroku;

    @Before
    public void setUp() {
        url = "https://restful-booker.herokuapp.com";
        specHeroku = new RequestSpecBuilder()
                .setBaseUri(url)
                .build();
    }

    //variablelar;

    protected Response actualResponse;

}
