package RestApi.RestApi;

import org.json.JSONObject;
import org.testng.annotations.Test;



import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.http.Headers;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;

public class TestApi {

	@Test
	public void GetWeatherDetails() {
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		RequestSpecification httpRequest = RestAssured.given();

		Response response = httpRequest.request(Method.GET, "/New York");

		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);

		int statuscode = response.getStatusCode();
		System.out.println("The status code is  =>  " + statuscode);

		String statusline = response.getStatusLine();
		System.out.println("The status line is  =>  " + statusline);

		// Get all the header information
		Headers allheader = response.headers();

		for (Header headerinfo : allheader) {
			System.out.println("Header name =>" + headerinfo.getName() + " Header value =>" + headerinfo.getValue());
		}

	}

	@Test
	public void RegistrationSuccessful() {
		RestAssured.baseURI = "http://restapi.demoqa.com/customer";
		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		
		requestParams.put("FirstName", "john"); // Cast
		requestParams.put("LastName", "david");
		requestParams.put("UserName", "david121");
		requestParams.put("Password", "test");

		requestParams.put("Email", "test@gmail.com");
		request.body(requestParams.toString());
		Response response = request.post("/register");

		int statusCode = response.getStatusCode();
		//Assert.assertEquals(statusCode, "201");
		String successCode = response.jsonPath().get("SuccessCode");
		//Assert.assertEquals("Correct Success code was returned", successCode, "OPERATION_SUCCESS");
		System.out.println("StatusCode =>" + statusCode);
		System.out.println("SuccessCode =>" + successCode);
	}

}
