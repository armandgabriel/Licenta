package com.licenta.db.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.licenta.entity.User;

public class HandlerDBConnectionManager extends HandlerDBConnection {
	private final String contextPath = "db.properties";
	private final String usersProperties = "users.txt";
	Properties prop = new Properties();
	InputStream input = null;
	private String schemaName, name, user, pass;
	List<User> userList = new ArrayList<User>();
	List<String> header = new ArrayList<String>();
	User loggedUser = null;
	List<Integer> idList = new ArrayList<Integer>();

	@Override
	public void initConnection() {
		// TODO Auto-generated method stub
		System.out.println("Hello from initConnection : class " + getClass().toString());
		loadProperties();
		getUserList();
	}

	public boolean checkUser(String user, String pass) {
		if (!user.isEmpty() && !pass.isEmpty()) {
			for (User us : userList) {
				if (us.getUserName().equals(user) && us.getPassword().equals(pass)) {
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
			while ((line = reader.readLine()) != null) {
				if (counter == 0) {
					for (int i = 0; i <= 6; i++) {
						header.add(line.split("\\,", -1)[i]);
					}
				}
				if (header.size() > 0 && counter > 0) {
					idList.add(Integer.parseInt(line.split("\\,", -1)[0]));
					userList.add(new User(line.split("\\,", -1)[1], line.split("\\,", -1)[2], line.split("\\,", -1)[3],
							line.split("\\,", -1)[4], line.split("\\,", -1)[5], line.split("\\,", -1)[6]));
				}
				counter++;
			}
			System.out.println();
			for (String s : header) {
				System.out.print(s + "|");
			}
			for (User u : userList) {
				System.out.println(u.toString());
			}
		} catch (Exception e) {
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
			if (in != null)
				in.close();
		}
		return ret;
	}

	@Override
	public User getLoggedUserData() {
		// TODO Auto-generated method stub
		return loggedUser;
	}

	@Override
	public void signUser(User user) {

		String cale = "C:\\Users\\ARMANDGABRIELCAMNER\\git\\Licenta\\Licenta\\src\\main\\resources\\users.txt";
		//String cale2 = "/main/resources/users.txt";
		
		List<User> usList = new ArrayList<User>();
		File f = new File(cale);
		String l = null;
		int lineIndex = 0;
		try {
			InputStream in = new FileInputStream(f);//HandlerDBConnectionManager.class.getResourceAsStream(cale);
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader bfr = new BufferedReader(isr);
		

		
			while ((l = bfr.readLine()) != null) {
				if (lineIndex > 0) {
					System.out.println(l);
					usList.add(new User(l.split("\\,", -1)[1], l.split("\\,", -1)[2], l.split("\\,", -1)[3],
							l.split("\\,", -1)[4], l.split("\\,", -1)[5], "no_history"));
				}
				lineIndex++;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if (usList.size() > 0) {
			for (User u : usList) {
				System.out.println(u.toString());
			}
		}
		usList.add(user);
		PrintStream ps = null;
		try {
			ps = new PrintStream(cale);
			lineIndex = 0;
			ps.println("id,user,pass,age,name,role,history");
			for(User u : usList)
			{
				ps.println(lineIndex+","+u.getUserName()+","+u.getPassword()
				+","+u.getAge()+","+u.getName()
				+","+u.getRole()+","+u.getHistory());
				lineIndex++;
			}
			ps.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ps.close();
		}
		
		
	}

}
