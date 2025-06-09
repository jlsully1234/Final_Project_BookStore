package book.store.controller.model;

import book.store.entity.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

/* The @Data annotation is a powerful Lombok feature that automatically generates all the boilerplate code for a class, 
 * including getters, setters, equals(), hashCode(), and toString() methods for the class.
 * 
 * The @NoArgsConstructor annotation is a feature of Lombok. It automatically generates a no-argument constructor for your class.
 * Used when you need a default constructor but don’t want to manually write it out.
 * 
 * The BookStoreCustomer class is a data transfer object (DTO) that encapsulates information about a customer, including its details 
 * and associated BookStore. BookStoreCustomer is designed to represent data related to a customer. 
 */

@Data
@NoArgsConstructor

//Class Fields contains several private fields that store information about a customer

public class BookStoreCustomer {
   private Long customerId;
   private String customerFirstName;
   private String customerLastName;
   private String customerEmail;
   
 /* This constructor initializes a BookStoreCustomer object with the details of a Publisher object by copying 
  * its ID, first name, last name, and email.
  */
   
   
public BookStoreCustomer (Customer customer) {
	customerId = customer.getCustomerId();
	customerFirstName = customer.getCustomerFirstName();
	customerLastName = customer.getCustomerLastName();
	customerEmail = customer.getCustomerEmail();
}


}
