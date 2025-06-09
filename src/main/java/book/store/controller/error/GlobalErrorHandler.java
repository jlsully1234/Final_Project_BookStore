package book.store.controller.error;

import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lombok.extern.slf4j.Slf4j;

/* This is a Java class that acts as a global error handler for a Spring Boot application.
 * 
 * The class GlobalErrorHandler is annotated with @RestControllerAdvice, which indicates that it provides centralized 
 * exception handling across all @RequestMapping methods in the application. 
 * 
 * The @Slf4j annotation is used to enable logging within the class.
 * 
 * Exception Handling: The method handleNoSuchElementException is annotated with @ExceptionHandler(NoSuchElementException.class), which means 
 * it will handle exceptions of type NoSuchElementException that occur in the application.
 * 
 * @ResponseStatus: The method is also annotated with @ResponseStatus(code = HttpStatus.NOT_FOUND), which sets the HTTP response status to 
 * 404 (Not Found) when a NoSuchElementException is caught.
 * 
 * Logging and Response: When a NoSuchElementException is caught, the method logs the exception message using the log.error method.
 * 
 * It then returns a Map containing a single entry with the key "message" and the value being the 
 * exception's string representation. This map is typically converted to a JSON response body.
 */

@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler {
 @ExceptionHandler(NoSuchElementException.class)
 @ResponseStatus(code = HttpStatus.NOT_FOUND)
 public Map<String,String>handleNoSuchElementException(
		 NoSuchElementException ex) {
	 log.error("Exception: {}", ex.toString());
	 return Map.of("message", ex.toString());
	 
 }

 }
