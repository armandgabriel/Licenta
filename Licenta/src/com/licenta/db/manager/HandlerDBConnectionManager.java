package com.licenta.db.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class HandlerDBConnectionManager extends HandlerDBConnection {
	private final String contextPath = "db.properties";
	Properties prop = new Properties();
	InputStream input = null;
	@Override
	public void initConnection() {
		// TODO Auto-generated method stub
		System.out.println("Hello from initConnection : class " + getClass().toString());
		loadProperties();
	}

	private void loadProperties() {
		// TODO Auto-generated method stub
		System.out.println("===========================================");
		System.out.println(this.getClass().getClassLoader().toString());
		System.out.println("===========================================");
		try {
			prop = loadProperties("db.properties");
			System.out.println("Name: " + prop.getProperty("name"));
			System.out.println("Schema: " + prop.getProperty("schema"));
			System.out.println("Username: " + prop.getProperty("user"));
			System.out.println("Password: " + prop.getProperty("pass"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getConnectionStatus() {
		// TODO Auto-generated method stub
		return "Hello";
	}
	
	Properties loadProperties(String filename) throws IOException {
	    Properties ret = new Properties();
	    InputStream in = null;
	    try {
	        in = getClass().getClassLoader().getResourceAsStream(filename);
	        ret.load(in);
	    } finally {
	        if (in != null) in.close();
	    }
	    return ret;
	}

}
