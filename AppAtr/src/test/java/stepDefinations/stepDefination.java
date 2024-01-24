package stepDefinations;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ApiPath;

public class stepDefination {
	RequestSpecification reqSpec;
	Response response;

	@Given("I want to write a step with precondition")
	public void i_want_to_write_a_step_with_precondition() {
		System.out.println("Given");
	}
	@Given("some other precondition")
	public void some_other_precondition() {
		System.out.println("and Given");
	}
	@When("I complete action")
	public void i_complete_action() {
		System.out.println("when");
	}
	@Then("I validate the outcomes")
	public void i_validate_the_outcomes() {
		System.out.println("then");
	}
	
	@Given("I want to write a step with name")
	public void i_want_to_write_a_step_with_name() {
		//System.out.println(name);
		System.out.println("Second scenario Given");
	}
	@When("I check for the {int} in step")
	public void i_check_for_the_in_step(int value) {
		System.out.println(value);
		System.out.println("Second scenario When");
	}
	@Then("I verify the status in step")
	public void i_verify_the_success_in_step() {
		//System.out.println(status);
		System.out.println("Second scenario Then");
	}
	
	@Given("I want to write a step with {string}")
	public void i_want_to_write_a_step_with(String name) {
		System.out.println(name);
		System.out.println("Second scenario Given");
	}
	@Then("I verify the {string} in step")
	public void i_verify_the_in_step(String status) {
		System.out.println(status);
		System.out.println("Second scenario Then");
	}
	
	//Step definitions for Place API
	
	@Given("Add Place Payload")
	public void add_place_payload() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
			
		reqSpec = given()			
			.queryParams("Key", "qaclick123")
			.header("Content-Type","application/json")
			.body("{\r\n"
					+ "  \"location\": {\r\n"
					+ "    \"lat\": -38.383494,\r\n"
					+ "    \"lng\": 33.427362\r\n"
					+ "  },\r\n"
					+ "  \"accuracy\": 50,\r\n"
					+ "  \"name\": \"Frontline house\",\r\n"
					+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
					+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
					+ "  \"types\": [\r\n"
					+ "    \"shoe park\",\r\n"
					+ "    \"shop\"\r\n"
					+ "  ],\r\n"
					+ "  \"website\": \"http://google.com\",\r\n"
					+ "  \"language\": \"French-IN\"\r\n"
					+ "}\r\n"
					+ "");
	}
	@When("user calls {string} with post http request")
	public void user_calls_with_pst_http_request(String apiPath ) {
		response = reqSpec.when()
		.post("/maps/api/place/add/json")
		.then().extract().response();
		ApiPath api = ApiPath.valueOf(apiPath);
		
		System.out.println(api.getApiPath());

	}
	@Then("the API got status code {int}")
	public void the_api_got_status_code(Integer statusCode) {
		assertEquals(response.getStatusCode(), statusCode.intValue());
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		String res = response.asString();
		JsonPath js = new JsonPath(res);
		assertEquals(js.get(key),value);
	}
}
