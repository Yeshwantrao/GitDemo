package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@deletePlace")
	public void beforeScenario() throws IOException
	{
		stepDefinition m = new stepDefinition();
		
		if(stepDefinition.place_id==null)
		{
		System.out.println("testing");
		
		System.out.println("Changes in develop branch");
		System.out.println("In different time zone");
		
			
		m.add_Place_Payload_with("Shetty", "bb", "cc");
		m.user_calls_using_http_request("AddPlaceAPI", "POST");
		m.verify_place_Id_created_maps_to_using("Shetty", "getPlaceAPI");
		}

	}

}
