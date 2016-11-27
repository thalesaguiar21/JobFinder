package framework.db;
import java.sql.*;
import java.util.List;
import java.util.Map;

public abstract class DBManager {
	
	protected static String JDBC_DRIVER;
	protected static String DB_URL;
	protected static String adm_user;
	protected static String adm_paswd;
	protected Connection connection;
	protected Statement stmt;
	
	public abstract void openConnection();
	
	public abstract void closeConnection();
	
	public abstract void update(String sql);
	
	public abstract List<Map<String, Object>> select(String sql);
}
