package book.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* The following code is a basic set up for a Spring Boot application
 *
 * public class BookStore Application is the definition of the class 
 */


@SpringBootApplication
public class BookStoreApplication {

/*	Main method declaration, standard signature for the main method in Java.  
 * It is public, meaning it can be accessed from outside the class. It is static, 
 * so it can be called without creating an instance of the class. 
 */ 
	
	public static void main(String[] args) {
		
// This line is responsible for launching the Spring Boot application.
		
		SpringApplication.run(BookStoreApplication.class, args);

	}

}
