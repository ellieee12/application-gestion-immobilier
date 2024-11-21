package modeleDAO;

import java.sql.Connection;
import java.sql.DriverManager;


public class MySQLCon {
	private static MySQLCon mySQLCon;
	protected Connection con;
	
	private MySQLCon() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/saes3","root","");
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static synchronized MySQLCon getInstance() {
		if (mySQLCon == null) {
			mySQLCon = new MySQLCon();
		}
		return mySQLCon;
	}
	
	public Connection getConnection() {
		return this.con;
	}
	
	public void disconnect() {
		try {
			this.con.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
