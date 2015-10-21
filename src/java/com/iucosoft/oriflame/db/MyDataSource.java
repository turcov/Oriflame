
package com.iucosoft.oriflame.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author iurasun
 */
public class MyDataSource {
    private static final Logger LOG = Logger.getLogger(MyDataSource.class.getName());
    
    
    private static MyDataSource instance;

    public static MyDataSource getInstance() {
         if(instance == null){
           instance = new MyDataSource();
         }
        return instance;
    }

    
    
    private static Connection connection;
    
    private static DataSource dataSource;
    
    private MyDataSource() {
        
        
         try {
            //  jdbc/centricdb

            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/oriflame");
            Connection conn = dataSource.getConnection();
            if(conn==null){
                LOG.log(Level.SEVERE, "Atentie conexiunea este nula!");
            }
            
            
        } catch (NamingException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        
        
        //createConnection();
    }
    
    
    public  Connection getConnection() throws SQLException{
         if(connection == null || connection.isClosed() ){
            createConnection() ;
         }
        return connection;
    }
    
    
    public  void closeConnection() throws SQLException{
         if(connection != null && !connection.isClosed() ){
            connection.close();
            connection=null;
         }
        
    }
    
    
    

    private static void createConnection() {
     try {
            //connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/webmvc", "root", "free");
         
            connection =dataSource.getConnection();
            
            LOG.info("connection created! ");
        } catch (SQLException ex) {
            Logger.getLogger(MyDataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
