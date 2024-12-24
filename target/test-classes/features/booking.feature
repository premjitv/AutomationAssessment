Feature: Validate createBookingAPI

 

    @smoke-AddBooking
    Scenario: Verify that Booking is done successfully with CreteBooking API call
    Given the API end point 
    When I make POST request
    Then API call got success with status code 200
    And status status description OK
    
    
    @smoke-not implemented
    Scenario: Verify that Booking is not possible with duplicate details
    Given the API end point 
    When I make POST request with an existing first name,lastname, email and Phone
    Then API call got failed with status code 409
   
    #@smoke-not implemented
    Scenario Outline: Verify the CreateBooking API with different input combinations
    Given the API endpoint for booking creation
    When I make a POST request with the following booking details:
        | firstname   | lastname    | totalprice | depositpaid | checkin    | checkout   | additionalneeds   |
        | <firstname> | <lastname>  | <price>    | <deposit>   | <checkin>  | <checkout> | <needs>           |
    Then API call <expected_result> with the status code as <status_code>
    

Examples:
    | firstname   | lastname   | phone                   | deposit | checkin     | checkout     | status_code |              
    | manu        | Kishan     | 123456789876543212345   | true    | 2024-12-25  | 2024-12-30   | 200         | 
    | john mathes | joseph new | 123456789               | false   | 2024-12-26  | 2025-01-09   | 200         | 
    |             | Mathew     | 150                       | true    | 2024-12-25  | 2024-12-30   |   400     | 
    
    
   
   
    Scenario: Verify that whether create booking API fails with an invalid endpoint
    Given an invalid API endpoint for booking creation
    When make a POST request
    Then API call failing with status code as 404
    And the response contains an the error message "Not Found"
   
   
    Scenario: Verify that createBookign API handles server error during booking
    Given an valid API endpoint for booking creation
    When make a POST request and server is down
    Then API call failing with status code as 500
    
   
    Scenario: Verify that cerateBookig with invalid format of data
    Given an invalid checkindate as "2024/12" 
    When make a POST request 
    Then API call failing with status code as 400
   
   
    #@smoke
    
   
   #Scenario: Retrieve booking details using bookingID
    Given having a valid booking ID
    When I send a GET request to retrieve booking details for the given booking ID
    Then the response status code should be 200
    And the response should include the booking ID
    And the response should include the hotel name
    And the response should include the guest name
    And the response should include the check-in and check-out dates
    
    
    @smoke-not implemented
   	Scenario: Verify that Get booking details with Invalid bookingID
    Given having a invalid booking ID
    When send a GET request to retrieve booking details for the given booking ID
    Then the response status code should be 404
    
    
    
    
    
     #@Update Booking detail
    
    Scenario: Verify that booking details are updated with new guest name,roomID,Checkindates and Checkoutdates
    Given I have the booking ID "12345"
    When I send a PUT request to update the booking with new details
    Then the response status code should be 200
    And the response should reflect the updated guest name "premji"
    And the response should reflect the updated check-in date "2024-12-25"
    And the response should reflect the updated check-out date "2024-12-30"
    And the response should reflect the updated room ID "105"
    
    
     #@Update Booking detail negative
    
    Scenario: Verify that booking details are not updated with invalid bokking ID
    Given have an invalid booking ID "12345"
    When I send a PUT request to update the booking with new details
    Then the response status code should be 404
   
         	
  	Scenario:Verify that booking details can be deleted successfully using a valid booking ID
    Given having the booking ID "12345"
    When send a DELETE request to delete the booking with the ID
    Then the response status code should be 200
    And the response should confirm the deletion of the booking with ID "12345"