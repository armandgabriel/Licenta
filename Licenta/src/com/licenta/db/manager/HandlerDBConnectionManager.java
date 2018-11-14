package com.licenta.db.manager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import com.licenta.entity.User;

public class HandlerDBConnectionManager extends HandlerDBConnection {
	private final String contextPath = "db.properties";
	private final String usersProperties = "users.txt";
	Properties prop = new Properties();
	InputStream input = null;
	private String schemaName,name,user,pass;
	List<User> userList = new ArrayList<User>();
	List<String> header = new ArrayList<String>();
	User loggedUser = null;
	@Override
	public void initConnection() {
		// TODO Auto-generated method stub
		System.out.println("Hello from initConnection : class " + getClass().toString());
		loadProperties();
		getUserList();
	}
	
	public boolean checkUser(String user, String pass)
	{
		if(!user.isEmpty()&&!pass.isEmpty())
		{
			for(User us : userList) {
				if(us.getUserName().equals(user) && 
						us.getPassword().equals(pass)) {
					loggedUser = us;
					return true;
				}
					
			}
		}
		return false;
	}

	private void getUserList() {
		// TODO Auto-generated method stub
		try {
			InputStream filePath = getClass().getClassLoader().getResourceAsStream(usersProperties);
			BufferedReader reader = new BufferedReader(new InputStreamReader(filePath));
			int counter = 0;
			String line = "";
			while((line = reader.readLine()) != null)
			{
				if(counter == 0)
				{
					for(int i = 1;i<=6;i++)
					{
						header.add(line.split("\\,",-1)[i]);
					}
					counter++;
				}
				if(header.size()>0 && counter > 0)
				{
					userList.add(new User(line.split("\\,",-1)[1],
							line.split("\\,",-1)[2],
							line.split("\\,",-1)[3],
							line.split("\\,", -1)[4],
							line.split("\\,", -1)[5],
							line.split("\\,", -1)[6]));
				}
			}
			System.out.println();
			for(String s : header)
			{
				System.out.print(s + "|");				
			}
			for(User u : userList)
			{
				System.out.println(u.toString());
			}
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	private void loadProperties() {
		// TODO Auto-generated method stub
		System.out.println("===========================================");
		System.out.println(this.getClass().getClassLoader().toString());
		System.out.println("===========================================");
		try {
			prop = loadProperties("db.properties");
			System.out.println("Name: " + prop.getProperty("name"));
			this.name = prop.getProperty("name");
			System.out.println("Schema: " + prop.getProperty("schema"));
			this.schemaName = prop.getProperty("schema");
			System.out.println("Username: " + prop.getProperty("user"));
			this.user = prop.getProperty("user");
			System.out.println("Password: " + prop.getProperty("pass"));
			this.pass = prop.getProperty("pass");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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

	@Override
	public User getLoggedUserData() {
		// TODO Auto-generated method stub
		return loggedUser;
	}

}
