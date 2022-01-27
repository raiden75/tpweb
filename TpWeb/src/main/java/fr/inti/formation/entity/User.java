package fr.inti.formation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userID;
	private String login;
	private String password;
	@Column(name = "connection_number")
	private int connectionNumber;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "emp_id", nullable = false)
	private Employee emp;
	
	public User(int userID, String login, String password, int connectionNumber) {
		super();
		this.userID = userID;
		this.login = login;
		this.password = password;
		this.connectionNumber = connectionNumber;
	}
	
	public User(String login, String password, int connectionNumber) {
		super();
		this.login = login;
		this.password = password;
		this.connectionNumber = connectionNumber;
	}
	
	public User() {
		super();
	}


	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getConnectionNumber() {
		return connectionNumber;
	}

	public void setConnectionNumber(int connectionNumber) {
		this.connectionNumber = connectionNumber;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	
	public int getEmpId() {
		return emp.getEmpId();
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", login=" + login + ", password=" + password + ", connectionNumber="
				+ connectionNumber + ", emp=" + emp + "]";
	}
	
	

}
