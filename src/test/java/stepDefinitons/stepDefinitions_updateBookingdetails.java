package stepDefinitons;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import File.Payload;
import File.util;
public class stepDefinitions_updateBookingdetails {
	
  private String bookingId;
//	String actualfirstname="premji";
//	String actualCheckindate="2024-12-21";	
//	String ActualroomID="105";
  private String response;
  
  @Given("I have the booking ID {string}")
  public void i_have_the_booking_id(String bookingId) {
      // get booking ID for using it in the PUT request
      this.bookingId = bookingId;
  }

  @When("I send a PUT request to update the booking with new details")
  public void i_send_a_put_request_to_update_the_booking_with_new_details() {
      
      String updatedPayload = Payload.UpdateBooking();
      
      // Send PUT request to update the booking
      response = given()
          .queryParam("id", bookingId)
          .header("token", "Token")  // Replace with actual token
          .body(updatedPayload)
          .when()
          .put("/booking/" + bookingId)  // adding bookingid to the endpoint 
          .then()
          .assertThat()
          .statusCode(200)
          .extract()
          .response()
          .asString();
  }

  @Then("the response status code should be {int}")
  public void the_response_status_code_should_be(Integer expectedStatusCode) {
      // asserting  the status code
      int actualStatusCode = Integer.parseInt(response);
      assertEquals(expectedStatusCode.intValue(), actualStatusCode);
  }

  @Then("the response should include the updated booking ID {string}")
  public void the_response_should_include_the_updated_booking_id(String expectedBookingId) {
      JsonPath js = new JsonPath(response);
      String bookingId = js.getString("bookingId");
      assertEquals(expectedBookingId, bookingId);
  }

  @Then("the response should reflect the updated guest name {string}")
  public void the_response_should_reflect_the_updated_guest_name(String expectedGuestName) {
      JsonPath js = new JsonPath(response);
      String guestName = js.getString("firstname"); // Assuming "firstname" is the field in the response
      assertEquals(expectedGuestName, guestName);
  }

  @Then("the response should reflect the updated check-in date {string}")
  public void the_response_should_reflect_the_updated_check_in_date(String expectedCheckInDate) {
      JsonPath js = new JsonPath(response);
      String checkInDate = js.getString("checkin");
      assertEquals(expectedCheckInDate, checkInDate);
  }

  @Then("the response should reflect the updated check-out date {string}")
  public void the_response_should_reflect_the_updated_check_out_date(String expectedCheckOutDate) {
      JsonPath js = new JsonPath(response);
      String checkOutDate = js.getString("checkout");
      assertEquals(expectedCheckOutDate, checkOutDate);
  }

  @Then("the response should reflect the updated room ID {string}")
  public void the_response_should_reflect_the_updated_room_id(String expectedRoomId) {
      JsonPath js = new JsonPath(response);
      String roomId = js.getString("roomID");
      assertEquals(expectedRoomId, roomId);
  }
}
