package org.lkw.user.entity;

import java.io.Serializable;
import java.util.UUID;

public class UserPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String account;
	
	private String password;
	
	

	public UserPO() {
		super();
		this.id = UUID.randomUUID().toString().replace("-", "");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserPO [account=" + account + ", password=" + password + "]";
	}
	
	

}
