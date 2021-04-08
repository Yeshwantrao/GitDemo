package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResource;
import resources.TestDataBuild;
import resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

public class stepDefinition extends Utils {
	
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	JsonPath js;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		
		//System.out.println("A");
		 res=given().spec(requestspecification())				   
				   .body(data.addPlacePayLoad(name,language, address));
	}

		
	@When("user calls {string} using {string} http request")
	public void user_calls_using_http_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
		//System.out.println("B");
		
		APIResource resourceAPI = APIResource.valueOf(resource);
		
		System.out.println(resourceAPI.getResource());
		
	    // Write code here that turns the phrase above into concrete actions
		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST"))
		{	
		 response =res.when().post(resourceAPI.getResource());
		}
		else if(method.equalsIgnoreCase("GET"))
		{
			response =res.when().get(resourceAPI.getResource());	
		}
			
	     
	}

	@Then("Success with status code {int}")
	public void success_with_status_code(Integer int1) {
		
		//System.out.println("C");
	    // Write code here that turns the phrase above into concrete actions
	       
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String KeyValue, String ExpectedValue) {
		
		//System.out.println("D");
	    // Write code here that turns the phrase above into concrete actions
	      
	     
	   
	      assertEquals(getJsonPath(response,KeyValue), ExpectedValue); 
	}


@Then("verify place_Id created maps to {string} using {string}")
public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {
    // Write code here that turns the phrase above into concrete actions
	
	 place_id =  getJsonPath(response, "place_id");
	 res=given().spec(requestspecification()).queryParam("place_id", place_id);
	 user_calls_using_http_request(resource, "GET");
	 String actualName = getJsonPath(response, "name"); 
	 assertEquals(actualName, expectedName);
}

@Given("DeletePlace Payload")
public void deleteplace_Payload() throws IOException {
    // Write code here that turns the phrase above into concrete actions
		
   res = given().spec(requestspecification())
    .body(data.deletePlacePayLoad(place_id));

}
}          
