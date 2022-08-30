package demo.bookstores.logic.order;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.bookstores.logic.book.BookAggregate;
import demo.bookstores.logic.repository.IOrderRepository;

import demo.bookstores.logic.user.UserAggregate;

@Service
public class OrderAggregate {
	@Autowired
	private BookAggregate bookAggregate;
	@Autowired
	private UserAggregate userAggregate;
	@Autowired	
	private IOrderRepository orderRepository;

	public void addOrder(String isbno, String username, Order order) {
		
		var book=this.bookAggregate.findBook(isbno);
		var user =this.userAggregate.findUser(username);
		
		if (book != null && user !=null) {
			
			order.setUserId(user.getId());;
			order.setBookCode(book.getId());
			order.setPrice(calculateOrderPrice(isbno, order));
			book.setStock(book.getStock()- order.getOrderQuantity()); //reduce stock
			this.orderRepository.save(order);
			
			bookAggregate.updateBook(order.getBookCode(), book);
		}
		else {System.out.println("invalid user or book! ");}
		
}
	private double calculateOrderPrice(String isbno,Order order) {
		var book=this.bookAggregate.findBook(isbno);
		
		var price=(order.getOrderQuantity()	*  book.getPrice());
		return price;
		
	}
}