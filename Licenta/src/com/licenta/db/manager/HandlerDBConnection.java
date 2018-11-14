package com.licenta.db.manager;

import com.licenta.entity.User;

public abstract class HandlerDBConnection {
	public abstract void initConnection();
	public abstract String getConnectionStatus();
	public abstract boolean checkUser(String user, String pass);
	public abstract User getLoggedUserData();
}
