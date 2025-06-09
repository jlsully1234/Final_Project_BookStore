# Final_Project_BookStore
Week 18 Final Project. My project is called book-store. 
The Project implements a JPA RESTful Web API with full CRUD (Create, Read, Update and Delete) operations on a MySQL database. I created my project in Eclipse, a basic Maven project and Spring Boot API: 

The package structure includes all the layers like the controller, service, dao/repository, entities, and error handling, etc.). I have added my SQL scripts and Entity Relationship Diagram to the Final Eclipse package. 

I will demonstrate how my program works through the successful CRUD operations in a REST API Server, called Dbeaver. 

My database has four entities, a one to many relationship, between publisher and book_store, a many to one relationship, and a many to many relationship between customer and book_store, which contains a join table between customer Id’s and bookstore Id to make the book_store_customer table

I will perform ALL Crud operations on the Publisher entity.
POST a Publisher - CREATE
PUT a Publisher - UPDATE
DELETE publisher with ID 3 - DELETE
GET ALL Publishers - READ
GET Publisher by ID

Then one crud operation for each entity there after. 
 Post a Book_Store - CREATE
 Post a Customer - CREATE

