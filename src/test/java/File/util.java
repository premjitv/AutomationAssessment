package File;

import static io.restassured.RestAssured.given;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

public class util {
	public static String bookingId;
    public RequestSpecification requestSpec;

    public RequestSpecification requestSpecification1() throws IOException {
        // Setup logging
        PrintStream log = new PrintStream(new FileOutputStream("log.txt"));
               
        RestAssured.baseURI = "https://automationintesting.online";

        // Configure request specification
        requestSpec = given()
                          .header("Content-Type", "application/json")
                          .filter(new RequestLoggingFilter(log))    // Log request details
                          .filter(new ResponseLoggingFilter(log))
                          .body(Payload.AddBooking());
                          
        return requestSpec;
    }
    
    


}
