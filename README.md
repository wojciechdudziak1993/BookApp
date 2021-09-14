Instruction how to use application:


1. There is SQL script to generate Database and tables in path: src/main/resources/sqlinit/book.sql
2. In Application.properties you need to fill data to your database: 
localhost: [your port], and also:
    spring.datasource.username=[username]
    spring.datasource.password=[password]
3. Now you can just start app. 
4.Write URL in the web browser: 
http://localhost:8080/books/list

5.To getAllBooks from RestController use: GET http://localhost:8080/books
6.To addBook fro RestController use: PORT http://localhost:8080/books (with Postman)
