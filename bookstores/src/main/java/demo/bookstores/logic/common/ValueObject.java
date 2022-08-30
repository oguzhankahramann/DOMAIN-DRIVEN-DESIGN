package demo.bookstores.logic.common;

public class ValueObject {
	
	private String evAdresi;
	private String isAdresi;
	
	public ValueObject() {
		super();
	}
	
	public ValueObject(String evAdresi, String isAdresi) {
		super();
		this.evAdresi = evAdresi;
		this.isAdresi = isAdresi;
	}
	public String getEvAdresi() {
		return evAdresi;
	}
	public void s(String evAdresi) {
		this.evAdresi = evAdresi;
	}
	public String getIsAdresi() {
		return isAdresi;
	}
	public void setIsAdresi(String isAdresi) {
		this.isAdresi = isAdresi;
	}
}
