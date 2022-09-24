package demo.bookstores.logic.book;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import demo.bookstores.logic.common.Entity;
@Document(collection = "books")
public class Book extends Entity {
	@NotNull
	@NotBlank
	private String bookName;
	@NotNull
	@NotBlank
	private String isbno;
	
	@Min(value = 0)
	private int stock;
	
	@NotNull
	@Min(value = 0)
	private double price;
	
	ValueObjectBook valueObjectBook;
	
	@Version
	private Integer version;
	
	public Book(ObjectId id) {
		super(id);
		valueObjectBook=new ValueObjectBook();
		// TODO Auto-generated constructor stub
	}
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getIsbno() {
		return isbno;
	}
	public void setIsbno(String isbno) {
		this.isbno = isbno;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public ValueObjectBook getValueObjectBook() {
		return valueObjectBook;
	}
	public void setValueObjectBook(ValueObjectBook valueObjectBook) {
		this.valueObjectBook = valueObjectBook;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

		
	
	
	
}
	

