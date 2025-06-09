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
 * The BookStorePublisher class is a data transfer object (DTO) that encapsulates information about a publisher, including its details 
 * and associated BookStore. BookStorePublisher is designed to represent data related to a publisher. 
 */

@Data
@NoArgsConstructor
public class BookStorePublisher {

// Class Fields contains several private fields that store information about a publisher
	private Long publisherId;
	private String publisherName;
	private String publisherPhone;
	private String publisherEmail;
	
/* This code creates a private set named bookStores that will store unique BookStoreData objects, and it is initialized
 * as an empty HashSet	
 */ 
	
private Set<BookStoreData>bookStores = new HashSet<>();

/* This constructor initializes a BookStorePublisher object with the details of a Publisher object by copying 
 * its ID, name, phone number, and email.
 */

public BookStorePublisher (Publisher publisher) {
	publisherId = publisher.getPublisherId();
	publisherName = publisher.getPublisherName();
	publisherPhone = publisher.getPublisherPhone();
	publisherEmail = publisher.getPublisherEmail();
	
/* This code is transforming a collection of BookStore objects into a collection of BookStoreData objects by creating a new BookStoreData 
 * for each BookStore and adding it to the bookStores collection. 	
 */
	for(BookStore bookStore : publisher.getBookStores() ) {
		bookStores.add(new BookStoreData(bookStore));
}

}
}