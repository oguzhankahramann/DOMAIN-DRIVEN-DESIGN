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

import demo.bookstores.core.results.DataResult;
import demo.bookstores.core.results.ErrorResult;
import demo.bookstores.core.results.Result;
import demo.bookstores.core.results.SuccessDataResult;
import demo.bookstores.core.results.SuccessResult;
import demo.bookstores.logic.constants.Messages;
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
	
	
	   
	public Result updateUser(ObjectId id, User user) {
		
		
		this.userDao.modifyUser(id, user);
		return new SuccessResult(Messages.UserUpdatedSuccesfully);
	}
	
	
	public Result addUser(User user) {
		if(!this.isEmailExist(user.getEmail()).isSuccess() || !this.isTcKnExist(user.getTcKn()).isSuccess()) {
			
			return new ErrorResult("entered valid email or valid tc");
		}
		
		else if (!this.isUsernameExist(user.getUsername()).isSuccess()) {
			return new ErrorResult("entered valid username");
		}
		else {
			this.userDao.addUser(user);
			return new SuccessResult(Messages.UserAddedSuccesfully);
		}
		
	}
	public DataResult<List<User>> getAll() {

	    return new SuccessDataResult<List<User>>(this.userDao.findAllUser(),Messages.UserListed);
	}
	
	public User findUser(String Username) {
		return this.userDao.findOne(Username);
	}
	
	
	private Result isEmailExist(String email) {
		if(this.userDao.findByEmail(email)!= null) {
			return new ErrorResult("You have entered a used mail address!");
		}
		else{return new SuccessResult("You can use your mail address");}
 	 
	}

	private Result isUsernameExist(String username) {
		if(this.userDao.findByUsername(username)!= null) {
			return new ErrorResult("You have entered a used username address!");
		}
		else{return new SuccessResult("You can use your username address");}
 	 
	}

	private Result isTcKnExist(String tcKn) {
		if(this.userDao.findByUsername(tcKn)!= null) {
			return new ErrorResult("You have entered a used tckimlikno!");
		}
		else{return new SuccessResult("You can use your tckno");}
 	 
	}

	







	
	

}
