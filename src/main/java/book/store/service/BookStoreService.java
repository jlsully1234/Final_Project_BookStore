package book.store.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import book.store.controller.model.BookStoreCustomer;
import book.store.controller.model.BookStoreData;
import book.store.controller.model.BookStorePublisher;
import book.store.dao.BookStoreDao;
import book.store.dao.CustomerDao;
import book.store.dao.PublisherDao;
import book.store.entity.BookStore;
import book.store.entity.Customer;
import book.store.entity.Publisher;


/* The @Service annotation is a specialization of the @Component annotation in Spring. 
 * It is used to indicate that the class provides some business functionalities.
 * public class BookStoreService is the defined class
 */

@Service
public class BookStoreService {

/* The @Autowired annotation is used in the Spring Framework to automatically inject a dependency into a class. 
* The following lines declares private field book store. This allows the class to use bookStoreDao to perform data access
* operations without having to manually instantiate or configure it. 
*/

@Autowired	
private BookStoreDao bookStoreDao;

@Autowired
//This line declares a private field customer
private CustomerDao customerDao;

@Autowired
//This line declares a private field publisher
private PublisherDao publisherDao;

/*This method is designed to update a BookStore object with the corresponding data from a BookStoreData object,
 * effectively copying the fields from one to the other.
 */

private void copyBookStoreFields(BookStore bookStore,
	BookStoreData bookStoreData) {
  bookStore.setBookStoreId(bookStoreData.getBookStoreId());
  bookStore.setBookStoreName(bookStoreData.getBookStoreName());
  bookStore.setBookStoreAddress(bookStoreData.getBookStoreAddress());	
  bookStore.setBookStoreCity(bookStoreData.getBookStoreCity());
  bookStore.setBookStoreState(bookStoreData.getBookStoreState());
  bookStore.setBookStoreZip(bookStoreData.getBookStoreZip());
  bookStore.setBookStorePhone(bookStoreData.getBookStorePhone());
 }

/* This method is designed to update a Customer object with the corresponding data from a CustomerData object,
 * effectively copying the fields from one to the other.
 */

private void copyCustomerFields(Customer customer,
		BookStoreCustomer bookStoreCustomer) {
  customer.setCustomerId(bookStoreCustomer.getCustomerId());
  customer.setCustomerFirstName(bookStoreCustomer.getCustomerFirstName());
  customer.setCustomerLastName(bookStoreCustomer.getCustomerLastName());
  customer.setCustomerEmail(bookStoreCustomer.getCustomerEmail());
}

/* This method is designed to update a Publisher object with the corresponding data from a PublisherData object, 
 * effectively copying the fields from one to the other.
 */

private void copyPublisherFields(Publisher publisher,
		BookStorePublisher bookStorePublisher) {
  publisher.setPublisherId(bookStorePublisher.getPublisherId());
  publisher.setPublisherName(bookStorePublisher.getPublisherName());
  publisher.setPublisherPhone(bookStorePublisher.getPublisherPhone());
  publisher.setPublisherEmail(bookStorePublisher.getPublisherEmail());
}

/* The findOrCreatePublisher method either creates a new Publisher if no ID is provided or retrieves
 * an existing Publisher using the given ID.
 */

private Publisher findOrCreatePublisher(Long publisherId) {
	if(Objects.isNull(publisherId)) {
	return new Publisher();
			
	}
	return findPublisherById(publisherId);
}

/* The method findPublisherById is defined as private, it returns an object of type Publisher, the parameter
 * is publisherId, that the method is trying to find. There is a error handler if the Id is not found. There is
 * also a exception method.
 */

private Publisher findPublisherById(Long publisherId) {
	return publisherDao.findById(publisherId).orElseThrow(()
			-> new NoSuchElementException(
			"Publisher with Id=" + publisherId + " was not found."));
}

/* The method is named findOrCreateCustomer and it takes two parameters: bookStoreId and customerId, both of 
 * which are of type Long. The method checks to see if customer Id is null, It returns a new Customer if customerId is null
 * if no specific customer ID is provided, the method will create and return a new customer object.
 * Find and return an existing customer if customerId is not null
 */

private Customer findOrCreateCustomer(Long bookStoreId, Long customerId) {
	if(Objects.isNull(customerId)) {
		return new Customer();
	}
	return findCustomerById(bookStoreId, customerId);
}

/* Method retrieves a Customer by ID using the customerId by calling customerDao.findById(customerId). If the customer
 * is not found, it throws a NoSuchElementException with a message indicating that the customer with the specified ID was not found.
 * If the customer is found but is not a member of the specified book store, an IllegalArgumentException is thrown.
 */
private Customer findCustomerById(Long bookStoreId, Long customerId ) {
	 Customer customer = customerDao.findById(customerId).orElseThrow(() 
		 -> new NoSuchElementException(
			"Customer with Id=" + customerId + " was not found."));
	 
	 boolean found = false;
	 	
	 	for(BookStore bookStore : customer.getBookStores()) {
	 		if(bookStore.getBookStoreId() ==bookStoreId) {
	 			found = true;
	 			break;
	 		}
	 	}
	 	if(! found)  {
	 		throw new IllegalArgumentException("The customer with ID=" 
	 			+customerId + " is not a member of the book store with id=" + bookStoreId);
	 	}
	 	return customer;
	}

/* The method findBookStoreByID is defined as private, it returns a BookStore object and takes a Long type parameter named bookStoreId.
 * 
 * The method uses bookStoreDao.findById(bookStoreId) to search for a BookStore with the specified ID.
 * 
 * The findById method returns an Optional<BookStore>. The orElseThrow method is used to handle the case where the BookStore is not found. 
 * 
 * If the Optional is empty a NoSuchElementException is thrown with a message indicating that the Book Store with ID= followed by the 
 * bookStoreId was not found. 
 */

private BookStore findBookStoreByID(Long bookStoreId) {

	return bookStoreDao.findById(bookStoreId)
		.orElseThrow(() -> new NoSuchElementException(
		"Book Store with ID=" + bookStoreId + "was not found"));
}

/* The @Transactional annotation indicates that the method should be executed within a transaction. This means that 
 * all operations within the method are part of a single transaction, and if any operation fails, the transaction can be 
 * rolled back to maintain data integrity.
 * 
 * The method savePublisher takes a BookStorePublisher object as a parameter and returns a BookStorePublisher object.
 * The method retrieves the publisher ID from the bookStorePublisher object using getPublisherId().
 * 
 * The method calls findOrCreatePublisher(publisherId), which likely checks if a publisher with the given ID exists in the database. 
 * If it does, it retrieves the existing publisher; if not, it creates a new one.
 * 
 * The method copyPublisherFields(publisher, bookStorePublisher) copies relevant fields from the bookStorePublisher object
 * to the publisher object. 
 * 
 * The method saves the publisher object to the database using publisherDao.save(publisher). This operation returns a Publisher 
 * object that represents the saved entity in the database.
 * 
 * The last method returns a new BookStorePublisher object, initialized with the dbPublisher object, which is the saved publisher
 * from the database.
 */

@Transactional
public BookStorePublisher savePublisher(BookStorePublisher bookStorePublisher) {
	
	Long publisherId = bookStorePublisher.getPublisherId();
	Publisher publisher =findOrCreatePublisher(publisherId);
	
	copyPublisherFields(publisher, bookStorePublisher);
	
	Publisher dbPublisher = publisherDao.save(publisher);
	
	return new BookStorePublisher(dbPublisher);
	
}

/* The @Transactional annotation indicates that the method should be executed within a transaction.
 * 
 * The method findBookStoreByID starts by finding a BookStore object using the bookStoreId provided as a parameter
 * It retrieves the customer ID from the BookStoreCustomer object passed as a parameter.
 * The method then attempts to find an existing Customer using the bookStoreId and customerId. If the customer does not exist, 
 * it creates a new one. This is handled by the findOrCreateCustomer method.
 * 
 * The method updates the relationship between the Customer and the BookStore by adding the BookStore
 * to the customer's list of bookstores and vice versa. the Customer object is then saved to the database using a customerDao.save method. 
 * 
 * The last method returns a new BookStoreCustomer object that wraps the saved Customer object, reflecting any updates made during the process.
 */

@Transactional
public BookStoreCustomer saveCustomer(Long bookStoreId, BookStoreCustomer bookStoreCustomer) {
	BookStore bookStore = findBookStoreByID(bookStoreId);
	Long customerId = bookStoreCustomer.getCustomerId();
	Customer customer = findOrCreateCustomer(bookStoreId, customerId);
	
	copyCustomerFields(customer, bookStoreCustomer);
	
	customer.getBookStores().add(bookStore);
	bookStore.getCustomers().add(customer);
	
	Customer dbCustomer = customerDao.save(customer);
	
	return new BookStoreCustomer(dbCustomer);	
}


/* Retrieve Publishers: The method starts by calling publisherDao.findAll(), which presumably retrieves a 
 * list of all Publisher objects from a data source, such as a database. This list is stored in the variable publishers.
 * 
 * Initialize Result List: An empty LinkedList of BookStorePublisher objects is created and assigned to the variable result. 
 * This list will store the transformed Publisher objects.
 * 
 * Transform and Clear BookStores: The method iterates over each Publisher object in the publishers list. For each Publisher, 
 * it creates a new BookStorePublisher object using the Publisher as a parameter to the constructor. It then calls bsp.getBookStores().clear(),
 * which clears any existing bookstore associations from the BookStorePublisher object.
 * 
 * Add to Result List: The newly created BookStorePublisher object, with its bookstore list cleared, is added to the result list.
 * 
 * Return Result: The method returns the result list, which contains all the BookStorePublisher objects with cleared bookstore associations.
 */

public List<BookStorePublisher> retrieveAllPublishers() {
	 List<Publisher> publishers = publisherDao.findAll();	
	  
	  List<BookStorePublisher> result = new LinkedList<>();
		
	  for(Publisher publisher : publishers) {
		BookStorePublisher bsp = new BookStorePublisher(publisher);
		bsp.getBookStores().clear();
		    
	    result.add(bsp);
}
	  return result;
}


/* This annotation indicates that the method retrievPublisherById is transactional, meaning it will be executed
 * within a transaction context. The readOnly = true attribute specifies that the transaction is read-only,
 * 
 * The public BookStorePublisher retrievPublisherById(Long publisherId), method is public. It returns an object 
 * of type BookStorePublisher. The method takes a single parameter, publisherId, which is of type Long.
 * 
 * The method body return new BookStorePublisher(findPublisherById(publisherId)) calls another method, findPublisherById,
 * passing the publisherId as an argument.
 *
 * FindPublisherById is a method that retrieves a publisher's data from a data source, is then used to create a new 
 * instance of BookStorePublisher. BookStorePublisher is a class that represents a publisher entity, and the constructor
 * of this class is being used to initialize it with the data retrieved by findPublisherById.
 */

@Transactional(readOnly = true)
public BookStorePublisher retrievPublisherById(Long publisherId) {
	return new BookStorePublisher (findPublisherById(publisherId));
}

/* @Transactional(readOnly = false)
 * This annotation indicates that the method deletePublisherById should be executed within a transactional context. 
 * The readOnly = false attribute specifies that the transaction is not read-only, meaning it can perform write 
 * operations such as updates or deletes. This is important for ensuring data integrity and consistency when interacting 
 * with a database.
 *
 * The method, public void deletePublisherById(Long publisherId) is public, that takes a Long type parameter named publisherId. 
 * The purpose of this method is to delete a publisher record from the database using the provided publisherId.
 * 
 * Publisher publisher = findPublisherById(publisherId) method calls another method findPublisherById with the publisherId 
 * as an argument. This method is expected to retrieve a Publisher object from the database that matches the given publisherId.
 * The retrieved Publisher object is stored in the variable publisher.
 *
 * publisherDao.delete(publisher);The method then calls the delete method on publisherDao, passing the Publisher object as an 
 * argument. This operation is responsible for removing the publisher record from the database.
 */

@Transactional(readOnly = false)
public void deletePublisherById(Long publisherId) {
	Publisher publisher = findPublisherById(publisherId);
	publisherDao.delete(publisher);
}

/* The method is annotated with @Transactional(readOnly = false), indicating that it is transactional and can modify the database.
 * This ensures that the operations within the method are executed within a transaction, allowing for rollback in case of an error.
 * 
 * The method saveBookStore is designed to save or update a BookStore entity associated with a given Publisher.
 * 
 * The method retrieves a Publisher object using the findPublisherById method, which likely queries the database for a publisher
 *  with the given ID.
 *  
 * FindOrCreateBookStore retrieves a bookStore by Id, either finds an existing BookStore with the given ID or creates a new one if it 
 * doesn't exist.
 * 
 * the copyBookStoreFields method is used to copy data from the BookStoreData object to the BookStore entity
 * 
 * The BookStore entity's publisher is set to the retrieved Publisher object.
 * Add BookStore to Publisher: The BookStore is added to the list of bookstores associated with the Publisher.
 * 
 * The BookStore entity is saved to the database using bookStoreDao.save(bookStore). This operation persists the changes to the database.
 * 
 * The return value method returns a new BookStoreData object, which is constructed using the saved BookStore entity. 
 * 
 */

@Transactional(readOnly = false)
public BookStoreData saveBookStore(Long publisherId, BookStoreData bookStoreData) {
	Publisher publisher = findPublisherById(publisherId);
	 Long bookStoreId = bookStoreData.getBookStoreId();
	  BookStore bookStore = findOrCreateBookStore(bookStoreId, publisherId);
	  
	  copyBookStoreFields(bookStore, bookStoreData);
	  bookStore.setPublisher(publisher);
	  publisher.getBookStores().add(bookStore);
	  return new BookStoreData(bookStoreDao.save(bookStore));
	}

/* Method is named findOrCreateBookStore, it is private and takes two parameters: bookStoreId and publisherId
 * The method is intended to either find an existing BookStore object or create a new one based on the provided bookStoreId.
 * 
 * The method first checks if bookStoreId is null using Objects.isNull(bookStoreId) if the bookStoreId is null the code block
 * inside the if statement will execute the code block is currently empty, so nothing happens in this case.
 * 
 * Regardless of whether bookStoreId is null or not, the method returns a new instance of BookStore by calling new BookStore().
 */

private BookStore findOrCreateBookStore(Long bookStoreId, Long publisherId) {
	if (Objects.isNull(bookStoreId)) {
		
	}
	return new BookStore();

    }
}




