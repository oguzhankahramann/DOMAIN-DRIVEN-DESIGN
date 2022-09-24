package demo.bookstores.logic.repository.dao;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demo.bookstores.logic.repository.UserRepository;
import demo.bookstores.logic.user.User;
@Component
public class UserDao {
	@Autowired
	private UserRepository userRepository;
	
	
	public List<User> findAllUser(){
		return this.userRepository.findAll();
		}
	
	public User addUser(User user) {
		return this.userRepository.save(user);
	}
	
	
	public User modifyUser(ObjectId id,User user) {
		Optional<User> findUser =this.userRepository.findById(id);	
		User userProperties = findUser.get();
		userProperties.setId(user.getId());
		userProperties.setAd(user.getAd());
		userProperties.setSoyad(user.getAd());
		userProperties.setTcKn(user.getTcKn());
		userProperties.getUserDetail().setIsAdresi(user.getUserDetail().getIsAdresi());
		userProperties.getUserDetail().s(user.getUserDetail().getEvAdresi());
		userProperties.setPassword(user.getPassword());
		userProperties.setRole(user.getRole());
		userProperties.setEmail(user.getEmail());
		return this.userRepository.save(userProperties); 
	}
	public User findOne(String username) {
		
	 
		return this.userRepository.findByUsername(username);
	}
	
	public User findByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}
	public User findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}
	
	public User findByTcKn(String tcKn) {
		return this.userRepository.findByTcKn(tcKn);
	}
}
