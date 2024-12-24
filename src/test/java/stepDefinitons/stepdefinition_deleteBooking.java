package stepDefinitons;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import File.util;

public class stepdefinition_deleteBooking {
	
	
	private String bookingId;  // Variable to store booking ID
    private Response response; // Variable to store the response

    @Given("having the booking ID {string}")
    public void having_the_booking_id(String bookingId) {
        // Storing the booking ID
        this.bookingId = bookingId;
    
        //System.out.println("Booking ID: " + bookingId);
    }

    @When("send a DELETE request to delete the booking with the ID")
    public void send_a_delete_request_to_delete_the_booking_with_the_id() {
        // Send the DELETE request using the path parameter for booking ID
        response = given()
                .pathParam("id", bookingId)  
                .header("token", "Token")
                .when()
                .delete("/booking/{id}");
    }

    @Then("the response should confirm the deletion of the booking with ID {string}")
    public void the_response_should_confirm_the_deletion_of_the_booking_with_id(String expectedId) {
        // Validate the response status 
    	
    	response.then().assertThat().statusCode(200);

        // Extracting the response body
        String responseBody = response.getBody().asString();
        JsonPath js = new JsonPath(responseBody);

        // Verify that the response confirms the deletion
        String deletedBookingId = js.getString("deletedId");
        //System.out.println("Deleted Booking ID: " + deletedBookingId);

        // Assert that the deleted booking ID matches the expected ID
        assert deletedBookingId.equals(expectedId) : "Booking ID not found!";
    }


}
