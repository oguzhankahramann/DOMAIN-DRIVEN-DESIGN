package demo.bookstores.logic.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import demo.bookstores.core.results.DataResult;
import demo.bookstores.core.results.ErrorDataResult;
import demo.bookstores.core.results.Result;
import demo.bookstores.logic.book.Book;
import demo.bookstores.logic.book.BookAggregate;


@RestController
@RequestMapping("/books")
public class BooksController {
	@Autowired
	private BookAggregate bookAggregate;

	
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  public ErrorDataResult<Object> handleValidateException(
			  MethodArgumentNotValidException exceptions){	  
		  Map<String, String> validationErrors=new HashMap<String, String>();	
		  for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			  validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		  }
		  ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors,"Dogrulama Hataları");
		  return errors;
		  
	  }
	@GetMapping("/getall")
	@PreAuthorize("hasRole('USER')")
	public DataResult<List<Book>> getAll() {

		return this.bookAggregate.getAll();
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/addbook")
	public Result addBook(@Valid Book book) {
		return this.bookAggregate.addBook(book);
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/update/{id}")
	public void modifyBook(@Valid @PathVariable ObjectId id,Book book) {
		 this.bookAggregate.updateBook(id, book);
	}
	
}
