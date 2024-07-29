/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MySQLDB;

import com.microsoft.sqlserver.jdbc.SQLServerConnectionPoolDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MyPC
 */
public class ConnectDB {

    private Connection connection;

    public Connection open() {
        SQLServerDataSource db = new SQLServerConnectionPoolDataSource();
//        Connection con = null;
        db.setUser("sa");
        db.setServerName("TANTRUC");
        db.setPassword("123");
        db.setPortNumber(1433);
        db.setDatabaseName("QuanLyNhaHang");
        db.setEncrypt("true");
        db.setTrustServerCertificate(true);
        try {
            connection = db.getConnection();
            return connection;
//             System.out.println("Connect Success!");
        } catch (SQLServerException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
//        if (connection != null) {
//            System.out.println("Connection successful");
//        } else {
//            System.out.println("Failed to make connection!");
//        }
        return null;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet executeQuery(String strSQL) {
        try {
            ResultSet rs;
            Statement sm = connection.createStatement();
            rs = sm.executeQuery(strSQL);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int executeUpdate(String strSQL) {
        try {
            int result;
            Statement sm = connection.createStatement();
            result = sm.executeUpdate(strSQL);
            return result;
        } catch (SQLException ex) {
           // Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public Connection getConnection() {
        return connection;
    }

    public Connection getUserRole() {
        return getUserRole();
    }

 

    public boolean resetIdentity(String tableName) {
        try {
            String sql = "DBCC CHECKIDENT ('" + tableName + "', RESEED, 1)"; // Use DBCC CHECKIDENT for SQL Server
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
