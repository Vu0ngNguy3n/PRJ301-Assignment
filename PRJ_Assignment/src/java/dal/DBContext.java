/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
/**
 *
 * @author admin
 */
public abstract class DBContext<T> {

    protected Connection connection;

    public DBContext() {
        try {
            String user = "vuongbom123";
            String pass = "vuongbom123";
            String url = "jdbc:sqlserver://localhost\\MSSQLSERVER:1433;databaseName=PRJ";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public abstract void insert(T model);
    
    public abstract void update(T model);
    
    public abstract void delete(T model);
    
    public abstract T getStringId(String id);
    
    public abstract T get(int id);

    public abstract ArrayList<T> list();
    
    

}
