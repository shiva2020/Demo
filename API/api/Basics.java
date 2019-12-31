package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI="https://maps.googleapis.com";
		given().
		param("location","-33.8670522,151.1957362").
		param("radius","1500").
		param("key","AIzaSyCPTmx7r4GiTBfNk75zxXmyw5umduvI56M").log().all().
		
		when().
		get("/maps/api/place/nearbysearch/json").
		then().assertThat().statusCode(200).contentType(ContentType.JSON).and().
		body("results[0].name", equalTo("Sydney")).and().body("results[1].place_id", equalTo("ChIJFfyzTTeuEmsRuMxvFyNRfbk"))
		.and().header("Server", "scaffolding on HTTPServer2");

	}

}
