package restassured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import io.restassured.RestAssured;

public class FirstApiTest {

	@Test
	public void GetTest()
	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		given()
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
					+ "")
		.when()
			.post("/maps/api/place/add/json")
		.then().log().all().statusCode(200);
		
	}
	
	@Test
	public void GetCharacter()
	{
		given().log().all().header("Content-Type","application/json")
		.body("{\"query\":\"query($characterId: Int!)\\n{\\n  character(characterId: $characterId)\\n  {\\n    name\\n    type\\n  }\\n}\",\"variables\":{\"characterId\":5462}}")
		.when().post("https://rahulshettyacademy.com/gq/graphql").then().log().all();
	}
	
	@Test
	public void CreateCharacter()
	{
		String characterName = "Minnie";
		given().log().all().header("Content-Type","application/json")
		.body("{\"query\":\"mutation\\n{\\n  createLocation(location: {name:\\\"Japan\\\", type:\\\"Asia\\\",dimension:\\\"098\\\"})\\n  {\\n    id\\n  }\\n  createCharacter(character:{name:\\\""+characterName+"\\\", type:\\\"disney\\\",status:\\\"alive\\\",species:\\\"mouse\\\",gender:\\\"Female\\\",image:\\\"sfd\\\",originId:6169,locationId:6169})\\n  {\\n    id\\n  }\\n}\",\"variables\":null}")
		.when().post("https://rahulshettyacademy.com/gq/graphql")
		.then().log().all();
	}
}
