package demo.bookstores.logic.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import demo.bookstores.logic.order.Order;
import demo.bookstores.logic.order.OrderAggregate;

@RestController
@RequestMapping("/orders")
public class OrdersController {
	@Autowired
	private OrderAggregate  orderAggregate;
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/add")
	public void addOrder(String isbno, String username, Order order) {
		
		this.orderAggregate.addOrder(isbno, username, order);
		
		}
	
}
	

