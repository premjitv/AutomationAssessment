#Feature: Validating createBooking

 

  #@smoke
 # Scenario: Verify that Booking is done successfully with CreteBooking API call
  
    #Given the API end point 
    #When I make POST request
    #Then API call got success with status code 200
    #And status status description OK
   
    @smoke
    
   Feature:Verify that Get booking details fetching all the details 
   Scenario: Retrieve booking details using bookingID
    Given having a valid booking ID
    When I send a GET request to retrieve booking details for the given booking ID
    Then the response status code should be 200
    And the response should include the booking ID
    And the response should include the hotel name
    And the response should include the guest name
    And the response should include the check-in and check-out dates
    
    @smoke
    
    Scenario: Verify that booking details are updated with new guest name and dates
    Given I have the booking ID "12345"
    When I send a PUT request to update the booking with new details
    Then the response status code should be 200
    And the response should include the updated booking ID "1122"
    And the response should reflect the updated guest name "premji"
    And the response should reflect the updated check-in date "2024-12-25"
    And the response should reflect the updated check-out date "2024-12-30"
    And the response should reflect the updated room ID "105"