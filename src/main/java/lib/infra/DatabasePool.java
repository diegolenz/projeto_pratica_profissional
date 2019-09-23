package br.edu.udc.sistemas.pwm2018.infra;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabasePool {

	private static DatabasePool databasePool = null;
	
	private Connection connectionList[];
	private Boolean connectionStatus[];
	
	private String userName;
	private String password;
	private String databaseName;
	private String host;
	private String port;
	private String driverClassName;
	private String url;
			
	private Integer maxConnections;
	private Boolean testOnBorrow;
	private String sqlTest;
	private Integer intervalConnectionTime;
	private Integer maxConnectionTry;
	
	private DatabasePool() throws Exception {
		InputStream input = null;
		try {
			Properties prop = new Properties();
			input = new FileInputStream(this.getClass().getResource("/").getFile() + "../config.properties");
			prop.load(input);
			
			this.userName = prop.getProperty("userName");
			this.password = prop.getProperty("password");
			this.databaseName = prop.getProperty("databaseName");
			this.host = prop.getProperty("host");
			this.port = prop.getProperty("port");
			this.driverClassName = prop.getProperty("driverClassName");
			this.url = prop.getProperty("url") + "://" + this.host + ":" + this.port + "/" + this.databaseName;
					
			this.maxConnections = Integer.parseInt(prop.getProperty("maxConnections"));
			this.testOnBorrow = prop.getProperty("testOnBorrow").equals("true");
			this.sqlTest = prop.getProperty("sqlTest");
			this.intervalConnectionTime = Integer.parseInt(prop.getProperty("intervalConnectionTime"));
			this.maxConnectionTry = Integer.parseInt(prop.getProperty("maxConnectionTry"));
			input.close();
			
			initialize();
		} finally {
			input.close();
		}
	}
	
	private void initialize() throws Exception {
		this.connectionList = new Connection[this.maxConnections];
		this.connectionStatus = new Boolean[this.maxConnections];
		
		Class.forName(this.driverClassName);
		
		for (int i = 0; i < this.maxConnections; i++) {
			this.connect(i);
		}
	}
	
	private void connect(Integer connectionId) throws Exception {
		this.connectionList[connectionId] = DriverManager.getConnection(this.url, this.userName, this.password);
		this.connectionList[connectionId].setAutoCommit(false);
		this.connectionStatus[connectionId] = true;		
	}
	
	private void testConnection(Integer connectionId) throws Exception {
		Connection con = this.connectionList[connectionId];
		if ((con == null) || (con.isClosed())) {
			this.connect(connectionId);
		} else {
			Statement stmt = null;
			try {
				stmt = con.createStatement();
				stmt.execute(this.sqlTest);
			} catch (SQLException e) {
				this.connect(connectionId);
			} finally {
				if (stmt != null) {
					stmt.close();
				}
			}
		}
	}
	
	public Connection getConnection() throws Exception {
		int count = 0;
		while (count < this.maxConnectionTry) {
			synchronized (DatabasePool.class) {
				for (int i = 0; i < this.maxConnections; i++) {
					if (this.connectionStatus[i]) {
						if (this.testOnBorrow) {
							this.testConnection(i);
						}
						this.connectionStatus[i] = false;
						return this.connectionList[i]; 
					}
				}
			}			
			count++;
			Thread.sleep(this.intervalConnectionTime);
		}
		throw new Exception("Não existem conexões disponíveis. Tente novamente.");
	}
	
	public void releaseConnection(Connection con) {
		for (int i = 0; i < this.maxConnectionTry; i++) {
			if (this.connectionList[i].equals(con)) {
				this.connectionStatus[i] = true;
				break;
			}
		}
	}
	
	public synchronized static DatabasePool getInstance() throws Exception {
		if (DatabasePool.databasePool == null) {
			DatabasePool.databasePool = new DatabasePool();
		}
		return DatabasePool.databasePool;
	}
	
}
