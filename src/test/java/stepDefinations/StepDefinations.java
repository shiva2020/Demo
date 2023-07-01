package stepDefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinations {
    RequestSpecification reqspec;
    RequestSpecification reqs;
    ResponseSpecification resspec;
    Response response;
    @Given("Add Place Payload")
    public void addPlacePayload() {
        AddPlace ap=new AddPlace();
        ap.setAccuracy(60);
        ap.setAddress("31, side layout, cohen 10");
        ap.setWebsite("https://rahulshettyacademy.com");
        ap.setLanguage("English");
        ap.setName("Garampalli House");
        ap.setPhone_number("8095789456");
        List<String> mylist = new ArrayList<String>();
        mylist.add("my park");
        mylist.add("park");
        ap.setTypes(mylist);
        Location l = new Location();
        l.setLang(33.427362);
        l.setLat(-38.383494);
        ap.setLocation(l);
        reqspec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
        reqs= given().spec(reqspec).body(ap);
        resspec = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();
    }
    @When("User calls {string} with post http request")
    public void userCallsWithPostHttpRequest(String arg0) {
        response = reqs.when().post("/maps/api/place/add/json").
                then().spec(resspec).extract().response();
        System.out.println(response.asString());
    }
    @Then("the API call is success with status code {int}")
    public void theAPICallIsSuccessWithStatusCode(Integer int1) {

        assertEquals(response.getStatusCode(),200);
    }

    @And("{string} in response body as {string}")
    public void inResponseBodyAs(String keyValue, String Expectedvalue) {
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        String val= js.get("status");
        System.out.println(val);
        assertEquals(js.get(keyValue).toString(), Expectedvalue);
    }
}
