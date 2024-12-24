Feature: Validating createBooking

 

  @smoke
  Scenario: Verify that Booking is done successfully with CreteBooking API call
  
    Given the API end point 
    When I make POST request
    Then API call got success with status code 200
    And status status description OK