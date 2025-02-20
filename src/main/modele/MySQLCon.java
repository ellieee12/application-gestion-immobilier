package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MySQLCon {
	private static MySQLCon mySQLCon;
	protected Connection con;
	private static final Logger logger = Logger.getLogger(BienDAO.class.getName());
	
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
	
	public void rollback() throws DAOException{
		try {
			if (this.con.getAutoCommit()) {
	            this.con.setAutoCommit(false);  // Disable auto-commit before rollback
	        }
	        this.con.rollback();
		} catch (SQLException e) {
			logger.log(Level.SEVERE,"Erreur de rollback",e);
			throw new DAOException("Erreurs de rollback",e);
		}
	}
	
	public void setAutocommit(boolean b) throws DAOException{
		try {
			this.con.setAutoCommit(b);
		} catch (SQLException e) {
			logger.log(Level.SEVERE,"Erreur de autocommit",e);
			throw new DAOException("Erreurs de autocommit",e);
		}
	}
}