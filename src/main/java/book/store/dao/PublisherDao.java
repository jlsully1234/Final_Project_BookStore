package book.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import book.store.entity.Publisher;

/* The code defines a repository interface for managing Publisher entities with a primary key of type Long, 
 * leveraging Spring Data JPA's capabilities to simplify database interactions.
 */
public interface PublisherDao extends JpaRepository<Publisher, Long> {

}
