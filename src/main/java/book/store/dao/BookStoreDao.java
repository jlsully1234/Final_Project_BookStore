package book.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import book.store.entity.BookStore;

/*  The code shows a BookStoreDao interface as a Spring Data JPA repository that provides CRUD operations for BookStore 
 *  entities with a primary key of type Long. It leverages the capabilities of JpaRepository to simplify data access
 *  in a Spring application.
 */

public interface BookStoreDao extends JpaRepository<BookStore, Long> {

}
