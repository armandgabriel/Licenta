package com.licenta.db.manager;
public class HandlerDBConnectionManager extends HandlerDBConnection {

	@Override
	public void initConnection() {
		// TODO Auto-generated method stub
		System.out.println("Hello from initConnection : class " + getClass().toString());
	}

	@Override
	public String getConnectionStatus() {
		// TODO Auto-generated method stub
		return "Hello";
	}

}
