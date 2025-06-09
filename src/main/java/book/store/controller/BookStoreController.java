package book.store.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import book.store.controller.model.BookStoreCustomer;
import book.store.controller.model.BookStoreData;
import book.store.controller.model.BookStorePublisher;
import book.store.service.BookStoreService;
import lombok.extern.slf4j.Slf4j;

/* @RestController Annotation: This annotation is used to define a controller in a Spring Boot application. 
 * 
 * @RequestMapping("/publisher") Annotation: This annotation is used to map HTTP requests to handler methods 
 * of MVC and REST controllers. The request starts with /publisher will be handled by this controller.
 * 
 * @Slf4j Annotation: stands for Simple Logging Facade for Java, and it provides a simple abstraction of various
 * logging frameworks. This annotation is part of the Lombok library, which automatically generates a logger field 
 * in the class. 
 *  
 * Public class BookStoreController: is the declaration of a public class named BookStoreController. It is the 
 * main class that will handle HTTP requests related to the /publisher path.
 */

@RestController
@RequestMapping("/publisher")
@Slf4j
public class BookStoreController {
	
/* @Autowired Annotation: This is a Spring Framework annotation used for dependency injection. It tells the Spring 
 * container to automatically inject a dependency into the marked field, constructor, or method. In this case, it is
 * used to inject a dependency into a field.
 * 
 * Private BookStoreService declares a private field named bookStoreService of type BookStoreService. 
 */
	
@Autowired
private BookStoreService bookStoreService;

/* @PostMapping is a Spring MVC annotation used to map HTTP POST requests to specific methods in a controller,
 * simplifying the process of handling POST requests in web applications.
 *  
 * @ResponseStatus(code = HttpStatus.CREATED) is used to automatically set the HTTP response status to 201 (Created) 
 * for a specific handler method in a Spring MVC application, indicating that a new resource has been successfully 
 * created as a result of the request.
 * 
 * Signature method is public, meaning it can be accessed from outside the class it is defined in.
 * It returns an object of type BookStorePublisher. The method is intended to create a new publisher 
 * in a bookstore system
 * 
 * Log.info("Creating publisher {}", bookStorePublisher);: This line logs an informational message.
 */ 

@PostMapping
@ResponseStatus(code = HttpStatus.CREATED)
public BookStorePublisher createPublisher(@RequestBody BookStorePublisher bookStorePublisher) {
	log.info("Creating publisher {}", bookStorePublisher);
	
// This line of code saves a publisher's information using the bookStoreService and returns the result of that operation.	
	
	return bookStoreService.savePublisher(bookStorePublisher);
}

/* @PutMapping("/{publisherId}"): This annotation maps HTTP PUT requests to this method. The {publisherId} in the URL path is a placeholder 
 * for the publisher's ID that will be passed as a path variable.
 * 
 * @PathVariable Long publisherId: This parameter extracts the publisherId from the URL path. It is used to identify which publisher's 
 * information needs to be updated. @RequestBody BookStorePublisher bookStorePublisher: This parameter takes the request body and maps it to a 
 * BookStorePublisher object. This object contains the updated data for the publisher.
 * 
 * Updating the Publisher ID: The method sets the publisherId of the bookStorePublisher object to the publisherId extracted from the URL. 
 * This ensures that the correct publisher is being updated.
 * 
 * Logging: The log.info("Updating book store {}",bookStorePublisher); line logs an informational message indicating that a bookstore publisher
 * is being updated. 
 * 
 * The method calls bookStoreService.savePublisher(bookStorePublisher); to save the updated publisher information. This involves 
 * updating the record in a database.
 * 
 * Return Value: The method returns the updated BookStorePublisher object, which may include any changes made during the save operation, 
 * such as updated time stamps or other automatically managed fields.
 * 
 * his code is part of a RESTful API that updates a publisher's information in a bookstore system by handling PUT requests, updating the
 * publisher's ID, logging the update, and saving the changes.
 */

@PutMapping("/{publisherId}")
public BookStorePublisher updatePublisher(@PathVariable Long publisherId,
  @RequestBody BookStorePublisher bookStorePublisher) {
 bookStorePublisher.setPublisherId(publisherId);
 log.info("Updating book store {}",bookStorePublisher);
 
 return bookStoreService.savePublisher(bookStorePublisher);

}

/* @GetMapping; This annotation indicates that the method is mapped to an HTTP GET request. It is typically used in Spring MVC to map web 
 * requests to specific handler methods in a controller.
 * 
 * The method is public, meaning it can be accessed from outside the class. It returns a List of BookStorePublisher
 * objects, which suggests that it retrieves multiple publisher records.
 */
  
@GetMapping
public List<BookStorePublisher> retrieveAllPublishers() {

/* Log.info("Retrieving all publishers") This line logs an informational message indicating that the process of retrieving all publishers is starting.
 * 
 * Returns the list of publishers obtained from the bookStoreService.
 */
	
	log.info("Retrieving all publishers");
return bookStoreService.retrieveAllPublishers();
}

/* This code defines a method in a Spring Boot application that handles HTTP GET requests to retrieve a publisher's information by their ID. 
 * 
 * When a request is made to the end point with a specific publisherId, the method logs the retrieval action and calls a service method to fetch the publisher's 
 * details from the database or another data source. The retrieved publisher information is then returned as a BookStorePublisher object.
 */

@GetMapping("/{publisherId}")
public BookStorePublisher retrievePublisherById(@PathVariable Long publisherId) {
	log.info("Retrieving publisher with Id={}", publisherId);
	return bookStoreService.retrievPublisherById(publisherId);
}

/* The code is a method in a Spring Boot application that handles HTTP DELETE requests to delete a publisher by their ID.
 * 
 * When a DELETE request is made to the end point with a specific publisher ID, the method logs the deletion action, calls a
 * service to perform the deletion, and returns and a confirmation message in a map indicating that the publisher with the 
 * specified ID has been deleted.
 */

@DeleteMapping("/{publisherId}")
public Map<String, String>deletePublisherById(@PathVariable Long publisherId) {
	log.info("Deleting publisher with ID={}", publisherId);
	bookStoreService.deletePublisherById(publisherId);
	
	return Map.of("message", "Publisher with Id=" + publisherId + " deleted.");
}

/* Code defines a Spiring Boot application that handles HTTP POST requests to the end point /{publisherId}/bookStore. 
 * 
 * When a request is made to this end point, he method insertBookStore is invoked. he method logs the creation of a new bookstore using the log.info statement.
 * It then calls the the method insertBookStore is invoked. he method logs the creation of a new bookstore using the log.info statement.
 *  
 * It calls the saveBookStore method of the bookStoreService to save the bookstore data associated with the given publisherId. The method returns the saved
 * BookStoreData object. 
 * 
 * The @ResponseStatus annotation indicates that a successful creation of a bookstore will result in an HTTP status code 
 * of 201 (CREATED) being returned to the client.
 */

@PostMapping("/{publisherId}/bookStore")
@ResponseStatus(code = HttpStatus.CREATED)
public BookStoreData insertBookStore(@PathVariable Long publisherId, @RequestBody BookStoreData bookStore) {
  log.info("Creating book store()", bookStore);
  return bookStoreService.saveBookStore(publisherId, bookStore);
}

/* This code defines a RESTful end point that allows clients to add a new customer to a specified bookstore by sending a POST request with the customer's 
 * details in the @RequestBody. 
 * 
 * Annotation @PostMapping("/{bookStoreId}/customer"): This annotation maps HTTP POST requests to the method. The URL pattern includes a path variable {bookStoreId}, 
 * which represents the ID of the bookstore to which a customer is being added.
 * 
 * @ResponseStatus(code= HttpStatus.CREATED): This annotation indicates that the HTTP response status should be 201 Created when the method successfully executes.
 * it is used to indicate that a new resources has been successfully created on the server.
 * 
 * Method Parameters:
 * @PathVariable Long bookStoreId: This parameter extracts the bookStoreId from the URL and converts it to a Long type. It identifies the specific bookstore to which
 * the customer will be added.
 * 
 * @RequestBody BookStoreCustomer bookStoreCustomer: This parameter binds the HTTP request body to a BookStoreCustomer object. It represents the customer data that is 
 * being sent in the request.
 * 
 * Logging: The method logs an informational message indicating that a customer is being added to a bookstore, including the customer details and the bookstore ID.
 * 
 * Return Statement: The method calls bookStoreService.saveCustomer(bookStoreId, bookStoreCustomer) to save the customer to the specified bookstore. It returns the 
 * BookStoreCustomer object, which likely contains the saved customer details.
 */

@PostMapping("/{bookStoreId}/customer")
@ResponseStatus(code= HttpStatus.CREATED)
public BookStoreCustomer addCustomerToBookStore(@PathVariable Long bookStoreId,	
  @RequestBody BookStoreCustomer bookStoreCustomer) {
	 log.info("Adding customer {} to book store with ID={}", bookStoreCustomer, 
			bookStoreId);
	 
	 return bookStoreService.saveCustomer(bookStoreId, bookStoreCustomer);
  }
}