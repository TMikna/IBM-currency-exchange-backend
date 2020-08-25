package currency.logging;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class userAction {
	 
	private @Id @GeneratedValue long id;
	private long userId;
	private Date date;
	private String action;
	
	public userAction() {}
	
	public userAction(long id, long userId, Date date, String action) {
		this.id = id;
		this.userId = userId;
		this.date = date;
		this.action = action;
	}
	
	public userAction(long userId, Date date, String action) {
		this.userId = userId;
		this.date = date;
		this.action = action;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return String.format("[Id=%d, userId='%d', date='%s' , action='%s']", id, userId, date, action);
	}
}
