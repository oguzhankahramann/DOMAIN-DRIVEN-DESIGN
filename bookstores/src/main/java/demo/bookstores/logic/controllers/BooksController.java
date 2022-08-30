package demo.bookstores.logic.controllers;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.bookstores.logic.book.Book;
import demo.bookstores.logic.book.BookAggregate;

@RestController
@RequestMapping("/books")
public class BooksController {
	@Autowired
	private BookAggregate bookAggregate;

	@GetMapping("/getall")
	public List<Book> getAll() {

		return this.bookAggregate.getAll();
	}
	  @PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/addbook")
	public void addUser(Book book) {
		this.bookAggregate.addBook(book);
	}
	  @PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/update/{id}")
	public void modifyBook(@PathVariable ObjectId id,Book book) {
		this.bookAggregate.updateBook(id, book);
	}
	
}
