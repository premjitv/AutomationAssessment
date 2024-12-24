package stepDefinitons;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import File.Payload;
import File.util;

public class stepdefinitions_createBooking extends util {

    private RequestSpecification requestSpec; 
    private ResponseSpecification responseSpec; 
    private Response response; // Store the response

    @Given("the API end point")
    public void the_api_end_point() throws IOException {
    	 // Initialize the requestSpec by calling the method from the util class
        requestSpec = requestSpecification1(); // This will set up your request specification
        // Initialize Response Specification
        responseSpec = expect()
                           .statusCode(200)
                           .statusLine(containsString("OK"));
    }
    @When("I make POST request")
    public void i_make_post_request() {
    	// POST request
        response = requestSpec.when().post("/booking/");
    }
    @Then("API call got success with status code {int}")
    public void api_call_got_success_with_status_code(Integer statusCode) {
    	 response.then().assertThat().statusCode(statusCode);
    }
    @Then("status status description OK")
    public void status_status_description_ok() {
    	 // Validate the response using response specification
        response.then().spec(responseSpec);
        
        JsonPath js = response.jsonPath();
        String roomid= js.getString("roomid");
    }

    
    
    
    
    
    
    
    
    
}