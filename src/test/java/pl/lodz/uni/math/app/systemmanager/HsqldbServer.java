package pl.lodz.uni.math.app.systemmanager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl.AclFormatException;

public class HsqldbServer extends Server{

	public static final String DATABASE = "mem:systeminfodb";

	public static final String DB_NAME = "systeminfodb";

	public static final String USER = "SA";

	public static final String PASSWORD = "";
	
	public static final int PORT_NUMBER = 9001;
	
	public HsqldbServer() {
		super();
		HsqlProperties properties = new HsqlProperties();
		properties.setProperty("server.database.0", DATABASE);
		properties.setProperty("server.dbname.0", DB_NAME);
		properties.setProperty("server.silent", false);
		properties.setProperty("server.trace", true);
		try {
			this.setProperties(properties);
		} catch (IOException e) {
			System.out.println("Error ocurred while reading properties. message: " + e.getMessage());
		} catch (AclFormatException e) {
			System.out.println("Error ocurred while setting up properties. message: " + e.getMessage());
		}
	}
	
	@Override
	public int start() {
		return super.start();
	}
	
	@Override
	public int stop() {
		return super.stop();
	}
	
	public static void main(String[] args) {
		HsqldbServer hsqldbServer =  new HsqldbServer();
		hsqldbServer.start();
		System.out.println("Hsqldb Server has started. Hit enter to stop it.");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		hsqldbServer.stop();
	}

}
