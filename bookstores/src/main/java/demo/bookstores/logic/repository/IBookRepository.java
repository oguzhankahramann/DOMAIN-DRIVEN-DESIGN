package demo.bookstores.logic.repository;

import org.bson.types.ObjectId;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import demo.bookstores.logic.book.Book;

@Repository
public interface IBookRepository extends MongoRepository<Book, ObjectId> {
 
	Book findByIsbno(String isbno);
	
	
	
	
}
