package stepDefinitons;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import File.util;

public class stepDefinition_getBookingdetails {
	
	
	public class StepDefinitions {

	    private String response;
	    private String bookingId;
	    private final String token = "Token"; 
	    String bookingID = util.bookingId;
	    @Given("having a valid booking ID")
	    public void having_a_valid_booking_id() {
	        
	        bookingId = bookingID; // get the booking Id from stepdefinitions_createBooking 
	        //System.out.println("Booking ID: " + bookingID);
	    }

	    @When("I send a GET request to retrieve booking details for the given booking ID")
	    public void i_send_a_get_request_to_retrieve_booking_details_for_the_given_booking_id() {
	        // GET request
	        response = given()
	                .queryParam("roomid", bookingId)
	                .header("token", token)
	                .when()
	                .get("/booking/")
	                .then()
	                .assertThat()
	                .statusCode(200)
	                .extract()
	                .response()
	                .asString();

	        System.out.println("Response: " + response);
	    }

	    @Then("the response status code should be {int}")
	    public void the_response_status_code_should_be(Integer expectedStatusCode) {
	        // Validating status code
	        int actualStatusCode = given()
	                .queryParam("roomid", bookingId)
	                .header("token", token)
	                .when()
	                .get("/booking/")
	                .then()
	                .extract()
	                .statusCode();

	        Assert.assertEquals(expectedStatusCode.intValue(), actualStatusCode);
	    }

	    @Then("the response should include the booking ID")
	    public void the_response_should_include_the_booking_id() {
	        JsonPath js = new JsonPath(response);
	        String returnedBookingId = js.getString("bookingID");
	        Assert.assertEquals(bookingId, returnedBookingId);
	    }

	    @Then("the response should include the hotel name")
	    public void the_response_should_include_the_hotel_name() {
	        JsonPath js = new JsonPath(response);
	        String hotelName = js.getString("hotelName");
	        Assert.assertNotNull("Hotel name is null!", hotelName);//asserting null for Hotel name
	        //System.out.println("Hotel Name: " + hotelName);
	    }

	    @Then("the response should include the guest name")
	    public void the_response_should_include_the_guest_name() {
	        JsonPath js = new JsonPath(response);
	        String guestName = js.getString("guestName");
	        Assert.assertNotNull("Guest name is null!", guestName);//asserting null for Guest name
	        System.out.println("Guest Name: " + guestName);
	    }

	    @Then("the response should include the check-in and check-out dates")
	    public void the_response_should_include_the_check_in_and_check_out_dates() {
	        JsonPath js = new JsonPath(response);
	        String checkInDate = js.getString("checkInDate");
	        String checkOutDate = js.getString("checkOutDate");
	        Assert.assertNotNull("Check-in date is null!", checkInDate);// asserting checkin and checkout date.
	        Assert.assertNotNull("Check-out date is null!", checkOutDate);
	        //System.out.println("Check-In Date: " + checkInDate);
	        //System.out.println("Check-Out Date: " + checkOutDate);
	    }
	}


}
