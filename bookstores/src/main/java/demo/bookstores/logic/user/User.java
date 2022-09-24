package demo.bookstores.logic.user;



import javax.validation.constraints.Email;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;


import demo.bookstores.logic.common.Entity;
import demo.bookstores.logic.common.ValueObject;
@Document(collection = "users")
public class User extends Entity{
	//solid'in S'sine aykiri fakat Java community bu sekilde yapıyor
	@NotNull
	@NotBlank
	private String ad;
	
	@NotNull
	@NotBlank
	private String soyad;
	
	@NotNull
	@NotBlank
	@Size(min = 11 )
	@Size(max = 11 )
	private String tcKn;
	
	
	private ValueObject userDetail;
	
	private String username;
	
	private String role;
	
	@Email
	private String email;
	
	private String password;
	
	
	
	public User(ObjectId id) {
		super(id);
		
	userDetail= new ValueObject();
		// TODO Auto-generated constructor stub
	}
	
	public ValueObject getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(ValueObject userDetail) {
		this.userDetail = userDetail;
	}

	public String getAd() {
		return ad;
	}
	public void setAd(String ad) {
		this.ad = ad;
	}
	public String getSoyad() {
		return soyad;
	}
	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}
	public String getTcKn() {
		return tcKn;
	}
	public void setTcKn(String tcKn) {
		this.tcKn = tcKn;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
