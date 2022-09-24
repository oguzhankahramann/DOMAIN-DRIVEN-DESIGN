package demo.bookstores.logic.order;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.bookstores.core.results.ErrorResult;
import demo.bookstores.core.results.Result;
import demo.bookstores.core.results.SuccessResult;
import demo.bookstores.logic.book.BookAggregate;
import demo.bookstores.logic.constants.Messages;
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

	public Result addOrder(String isbno, String username, Order order) {
		
		var book=this.bookAggregate.findBook(isbno);
		var user =this.userAggregate.findUser(username);
		
		
			
			order.setUserId(user.getId()); 
			order.setBookCode(book.getId());
			order.setPrice(calculateOrderPrice(isbno, order));
			book.setStock(book.getStock()- order.getOrderQuantity()); //reduce stock
			if(book.getStock()>=0) {
				this.orderRepository.save(order);
			bookAggregate.updateBook(order.getBookCode(),book);
			return new SuccessResult(Messages.OrderAdded);
			}
			else {return new ErrorResult(Messages.OrderNotAdded);}
			
		
		
	}
		private double calculateOrderPrice(String isbno,Order order) {
			var book=this.bookAggregate.findBook(isbno);
			
			var price=(order.getOrderQuantity()	*  book.getPrice());
			return price;
			
		}
		
	}


