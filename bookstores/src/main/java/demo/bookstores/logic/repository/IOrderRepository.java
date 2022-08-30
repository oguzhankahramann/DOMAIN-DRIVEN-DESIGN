package demo.bookstores.logic.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import demo.bookstores.logic.order.Order;
@Repository
public interface IOrderRepository extends MongoRepository<Order, ObjectId>{
	
}
