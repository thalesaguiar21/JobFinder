package db.mannager;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import framework.db.DBManager;

public class PostgreSQLJDBC extends DBManager{
	
	public PostgreSQLJDBC() {
		JDBC_DRIVER = "org.postgresql.Driver";
		DB_URL = "jdbc:postgresql://localhost:5432/JobFinderDB";
		adm_user = "postgres";
		adm_paswd = "admin";
		connection = null;
		stmt = null;
	}

	@Override
	public void openConnection() {
		
		System.out.println("Abrindo conexão com o PostgreSQL...");
		try{
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, adm_user, adm_paswd);
			connection.setAutoCommit(false);
		} catch (Exception e) {
			System.out.println("Erro ao tentar se conectar!");
			e.printStackTrace();
	        System.err.println(e.getClass().getName()+": "+e.getMessage());
	        System.exit(0);
	    }
	    System.out.println("A conexão foi realizada com sucesso!");
	}

	@Override
	public void closeConnection() {
		System.out.println("Fechando a conexão com o banco...");
		try {
			if(connection == null || connection.isClosed())
				System.out.println("Não conexões estabelecidas!");
			else
				connection.close();
		} catch (SQLException e) {
			System.err.println("Não foi possível fechar a conexão!");
			e.printStackTrace();
		}
		System.out.println("Conexão encerrada com sucesso!");
	}

	@Override
	public void update(String sql) {
		System.out.println("Executando a atualização " + sql + " ...");
		try{
			if(connection == null || connection.isClosed()) 
				openConnection();
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			connection.commit();
			closeConnection();
		}catch(Exception e){
			System.err.println("Erro ao executar atualização!");
			e.printStackTrace();
		}
	}

	@Override
	public List<Map<String, Object>> select(String sql) {
		List<Map<String, Object>> resultList = new  ArrayList<Map<String, Object>>();
		Map<String, Object> linha = null;
		
		try{
			if(connection == null || connection.isClosed()) 
				openConnection();
			stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			
			ResultSetMetaData metaData = result.getMetaData();
		    Integer columnCount = metaData.getColumnCount();
			
		    while (result.next()) {
		        linha = new HashMap<String, Object>();
		        for (int i = 1; i <= columnCount; i++) {
		        	linha.put(metaData.getColumnName(i), result.getObject(i));
		        }
		        resultList.add(linha);
		    }
			stmt.close();
		}catch(Exception e){
			System.err.println("Erro ao executar inserção!");
			e.printStackTrace();
	        System.err.println(e.getClass().getName()+": "+e.getMessage());
	        System.exit(0);
		}finally{
			closeConnection();
		}
		return resultList;
	}

}
