#Feature: Validating createBooking

 

  #@smoke
 # Scenario: Verify that Booking is done successfully with CreteBooking API call
  
    #Given the API end point 
    #When I make POST request
    #Then API call got success with status code 200
    #And status status description OK
   
    @smoke
    
   Feature: Get booking details 
   Scenario: Retrieve booking details using bookingID
    Given having a valid booking ID
    When I send a GET request to retrieve booking details for the given booking ID
    Then the response status code should be 200
    And the response should include the booking ID
    And the response should include the hotel name
    And the response should include the guest name
    And the response should include the check-in and check-out dates