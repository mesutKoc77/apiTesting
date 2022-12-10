package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Before;

public class HerokuAppBaseUrl {
    protected RequestSpecification specHeroku;
    String url = "https://restful-booker.herokuapp.com";

    @Before
    public void setUp() {
        specHeroku = new RequestSpecBuilder()
                .setBaseUri(url)
                .build();
    }

}
