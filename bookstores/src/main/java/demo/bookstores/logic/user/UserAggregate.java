package demo.bookstores.logic.user;



import java.util.HashSet;
import java.util.List;

import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import demo.bookstores.logic.repository.dao.UserDao;


@Service(value="myUserService")
public class UserAggregate implements UserDetailsService{


	@Autowired
	private UserDao userDao;
	
	
	 private Set<SimpleGrantedAuthority> getAuthority(User user) {
		 
	   Set<SimpleGrantedAuthority> authorities = new HashSet<>();
	        
	   authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
	        
	   return authorities;
	    }
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =  userDao.findOne(username);
		
		if (user==null) {
			throw new  UsernameNotFoundException("invalid username or password log-01");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
	}
	
	
	   
	public void updateUser(ObjectId id, User user) {
		
		
		this.userDao.modifyUser(id, user);
		System.out.println("USER MODIFIED SUCCESSFULLY");
	}
	
	
	public void addUser(User user) {
		
		this.userDao.addUser(user);
		System.out.println("USER SUCCESSFULLY ADDED !");
	}
	public List<User> getAll() {

	    return this.userDao.findAllUser();
	}
	
	public User findUser(String Username) {
		return this.userDao.findOne(Username);
	}
	


	


	







	
	

}
