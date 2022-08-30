package demo.bookstores.logic.order;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import demo.bookstores.logic.common.Entity;

@Document(collection = "orders")
public class Order extends Entity{
	
	public Order(ObjectId id) {
		super(id);
		// TODO value object field gectikten sonra newlemeyi unutma
	}

	private int orderQuantity;
	
	private double price;
	
	private ObjectId bookCode;
	
	private ObjectId userId;
	
	
	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ObjectId getBookCode() {
		return bookCode;
	}

	public void setBookCode(ObjectId bookCode) {
		this.bookCode = bookCode;
	}

	public ObjectId getUserId() {
		return userId;
	}

	public void setUserId(ObjectId userId) {
		this.userId = userId;
	}
	
	
}
