package book.store.controller.model;

import java.util.HashSet;
import java.util.Set;
import book.store.entity.BookStore;
import book.store.entity.Customer;
import book.store.entity.Publisher;
import lombok.Data;
import lombok.NoArgsConstructor;

/* The @Data annotation is a powerful Lombok feature that automatically generates all the boilerplate code for a class, 
 * including getters, setters, equals(), hashCode(), and toString() methods for the class.
 * 
 * The @NoArgsConstructor annotation is a feature of Lombok. It automatically generates a no-argument constructor for your class.
 * Used when you need a default constructor but don’t want to manually write it out.
 * 
 * The BookStoreData class is a data transfer object (DTO) that encapsulates information about a bookstore, including its details 
 * and associated customers. BookStoreData that is designed to represent data related to a bookstore. 
 */

@Data
@NoArgsConstructor
public class BookStoreData {
	
// Class Fields contains several private fields that store information about a bookstore

	private Long bookStoreId; 
	private String bookStoreName;
	private String bookStoreAddress;
	private String bookStoreCity;
	private String bookStoreState;
	private String bookStoreZip;
	private String bookStorePhone;
	
/* Declaration: private Set<BookStoreCustomer> customers;
 * This line declares a variable named customers that is a Set of BookStoreCustomer objects.
 * The Set interface in Java is a part of the Java Collections Framework and is used to store a collection of unique elements. It does not allow duplicate entries.
 * 
 * Initialization: = new HashSet<>();
 * This part initializes the customers variable with a new instance of HashSet.
 * HashSet is a class that implements the Set interface. It is backed by a hash table and does not guarantee any specific order of elements.
 * using HashSet, the customers set will store unique BookStoreCustomer objects, and it will not maintain any order of insertion.
 */
	
private Set<BookStoreCustomer> customers = new HashSet<>();

/*This code is a constructor for a class named BookStoreData. The constructor takes an object of type BookStore as a 
 * parameter and initializes the fields of the BookStoreData class with the corresponding values from the BookStore object. 
 */

public BookStoreData(BookStore bookStore) {
	bookStoreId = bookStore.getBookStoreId();
	bookStoreName = bookStore.getBookStoreName();
	bookStoreAddress = bookStore.getBookStoreAddress();
	bookStoreCity = bookStore.getBookStoreCity();
	bookStoreState = bookStore.getBookStoreState();
	bookStoreZip = bookStore.getBookStoreZip();
	bookStorePhone = bookStore.getBookStorePhone();
	
/* The code iterates over a collection of Customer objects obtained from a method call 
 * bookStore.getCustomers(). For each Customer object in this collection, it creates a new BookStoreCustomer object using the 
 * Customer object as a parameter to the constructor. It then adds this newly created BookStoreCustomer object to a collection named customers.	
 */
	for(Customer customer : bookStore.getCustomers() ) {
		customers.add(new BookStoreCustomer(customer));
	}
	

			
}
}
