/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatprogramserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author theph
 */
public class JDBCConnection {
    public static Connection cn = null;

    public static Connection getConnection() throws SQLException {
        if(cn==null){
            cn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "root");
              
        }
        return cn;
    }

    
}
