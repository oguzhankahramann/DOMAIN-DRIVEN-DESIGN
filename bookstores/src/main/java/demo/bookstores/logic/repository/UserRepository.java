package demo.bookstores.logic.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import demo.bookstores.logic.user.User;


@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
	User findByUsername(String username);
	User findByEmail(String email);
	User findByTcKn(String tcKn);
}
