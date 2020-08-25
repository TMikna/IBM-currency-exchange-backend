package currency.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FxRate {
	 
	private @Id @GeneratedValue Long id;
	private String date;
	private String currencyCode;
	private double rate;
	
	public FxRate() {}
	
	public FxRate (Long id, String date, String currencyCode, double rate) {
		this.id = id;
		this.date = date;
		this.currencyCode = currencyCode;
		this.rate = rate;
	}
	
	public FxRate (String date, String currencyCode, double rate) {
		this.date = date;
		this.currencyCode = currencyCode;
		this.rate = rate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.format("Things[Id=%d, date='%s', currencyCode='%s' , rate='%f']", id, date, currencyCode, rate);
	}
	
}
