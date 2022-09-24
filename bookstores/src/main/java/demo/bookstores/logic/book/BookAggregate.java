package demo.bookstores.logic.book;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.bookstores.core.results.DataResult;
import demo.bookstores.core.results.Result;
import demo.bookstores.core.results.SuccessDataResult;
import demo.bookstores.core.results.SuccessResult;
import demo.bookstores.logic.constants.Messages;
import demo.bookstores.logic.repository.IBookRepository;

@Service
public class BookAggregate {

	@Autowired
	private IBookRepository bookRepository;

	public Result addBook(Book book) {
		this.bookRepository.save(book);
		return new SuccessResult(Messages.BookAdded);
	}

	public DataResult<List<Book>> getAll() {

		return new SuccessDataResult<List<Book>>(this.bookRepository.findAll(),Messages.BookListed);

	}
	//begenmedim içeriye tasinabilir
	
	
	public void updateBook(ObjectId id, Book book) {
		Optional<Book> findBook = this.bookRepository.findById(id);	
		Book bookProperties =findBook.get();
		bookProperties.setId(book.getId());
		bookProperties.setBookName(book.getBookName());
		bookProperties.setIsbno(book.getIsbno());
		bookProperties.valueObjectBook.setAuthorName(book.valueObjectBook.getAuthorName());
		bookProperties.valueObjectBook.setAuthorSurname(book.valueObjectBook.getAuthorSurname());
		bookProperties.setPrice(book.getPrice());
		bookProperties.setStock(book.getStock());
		this.bookRepository.save(book);
		
		
		
	}
	public Book findBook(String isbno){
		return this.bookRepository.findByIsbno(isbno);
	}
}
