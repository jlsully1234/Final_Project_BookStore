package book.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import book.store.entity.Customer;

/* The code declares an interface named CustomerDao CustomerDao extends JpaRepository<Customer, Long>. This means that CustomerDao 
 * inherits all the methods provided by the JpaRepository interface. JpaRepository is a part of the Spring Data JPA framework, 
 * which provides a set of methods for performing CRUD (Create, Read, Update, Delete) operations and more on a database.
 */

public interface CustomerDao extends JpaRepository<Customer, Long> {

}
