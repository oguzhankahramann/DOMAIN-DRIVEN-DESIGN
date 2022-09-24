package demo.bookstores.logic.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.AuthenticationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import demo.bookstores.configuration.JwtUtility;
import demo.bookstores.core.results.DataResult;
import demo.bookstores.core.results.ErrorDataResult;
import demo.bookstores.logic.models.AuthToken;
import demo.bookstores.logic.user.LoginUser;
import demo.bookstores.logic.user.User;
import demo.bookstores.logic.user.UserAggregate;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
//EXCEPTİON HANDLING GLOBAL , USER MODEL(VERI TABANI MODELINI CONTROLLERA ACMADAN MAPPING YAPILMALI) , LOGLAMA(DETYAYLANDIRMAK ANLAMLI) 
//LOGLAR ICIN CORELATION ID EKLENEBILIR ,DOCKER  FILE EKLE ,READ WRITE CONNECTION A BOLUNEBILIYOR MU CQRS YAPMADAN
//RATE LIMITING (API GATEWAY MU UYGULAMADA MI ) ,CIRCUIT BREAKER YAPISI (BIR YERE ISTEK ATMAK LAZIM PUBLIC APILERDEN BIRINE USERS EKLENDIGINDE API CALL)
//SECRET MANAGEMENT PW SENSITIVE DATA, CONFIG MANAGEMENT SENSITIVE OLMAYAN KAYITLAR ICIN CONSULE DIYE BIR SERVIS VAR DISTRIBUTED CONFIG MANAGEMENT
//JAVADA YONTEMINU BILMA LAZIM
//DINAMIK CONFIG DEGISTIRME 
//CACHE REDIS , REDIS YOKSA CACHE PAKETI VARDIR SPRING BOOTUN CONTROLLER SEVIYESINDE
//TEST YAZMAM LAZIM KAÇ CESIT TEST VAR 
//UNIT TEST INTEGRATION TEST INT TESTTE MOCK KULLANIMI TDD ILE YAPMAYA CALIS YPAABLILIRSEM
//READ ME DOCKER KOMUTLARINI FALAN KOYABILIRIM 
//UYGULAMAYI MONITOR ETMEK APM BAGLANTISI YAPILABILIR , LOGLARI INCELEMEK ICIN ELASTIC FORMATINDA LOG BASABILIRIM
//STRATEGIC DDD TACTICAL DDD 
@RestController
@RequestMapping("/users")
public class UsersController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserAggregate userService;
	@Autowired
	private JwtUtility jwtUtility;
	
	
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException{
		
		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginUser.getUsername(),
						loginUser.getPassword()
						)
				
				);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String token = jwtUtility.generateToken(authentication);
		return ResponseEntity.ok(new AuthToken(token));
		
	}
	
	  @RequestMapping(value="/register", method = RequestMethod.POST)
	  	
	 public ResponseEntity<?> saveUser(@Valid @RequestBody User user) {
		return  ResponseEntity.ok( this.userService.addUser(user));
	 }
	  
	  @ExceptionHandler(MethodArgumentNotValidException.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  public ErrorDataResult<Object> handleValidateException(
			  MethodArgumentNotValidException exceptions){	  
		  Map<String, String> validationErrors=new HashMap<String, String>();	
		  for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			  validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		  }
		  ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors,"Dogrulama Hataları");
		  return errors;
		  
	  }
	  
	    @PreAuthorize("hasRole('USER')")
	    @RequestMapping(value="/getall", method = RequestMethod.GET)
	public DataResult<List<User>> getAll(){
		
		return this.userService.getAll();
	}
	    
}
	