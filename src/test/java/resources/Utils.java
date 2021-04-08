package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	 
	public static RequestSpecification req;
	public RequestSpecification requestspecification() throws IOException
	{

		PrintStream log = new PrintStream(new FileOutputStream("logging.txt", true));
		RequestSpecification req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURI")).addQueryParam("key", "qaclick123")
				 .addFilter(RequestLoggingFilter.logRequestTo(log))
				 .addFilter(ResponseLoggingFilter.logResponseTo(log))
				 .setContentType(ContentType.JSON).build();
		 return req;

    }
	public String getGlobalValue(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\Yeshwant\\eclipse-workspace\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
			
	}
	
	public String getJsonPath(Response response, String key)
	{
		String rep =  response.asString();
	    JsonPath js =  new JsonPath(rep);
	    return js.get(key).toString();
	}

}
