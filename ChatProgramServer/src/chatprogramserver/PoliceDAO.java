/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatprogramserver;

import ProgramLab.Police;
import java.awt.Color;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author theph
 */
public class PoliceDAO {
    public ArrayList<Police> getAll() throws SQLException{
        Statement s = JDBCConnection.getConnection().createStatement();
        ResultSet rs = s.executeQuery("select * from Polices");
        ArrayList<Police> results = new ArrayList<>();
        while(rs.next()){
            Police p = new Police();
            p.setId(rs.getInt(1));
            p.setName(rs.getString(2).trim());
            p.setDob(rs.getDate(3));
            //p.setCurrent_position(new Point(rs.getInt(4),rs.getInt(5)));
            p.setPosx(rs.getInt(4));
            p.setPosy(rs.getInt(5));
            p.setSpeed(rs.getInt(6));
            p.setAtk(rs.getInt(7));
           //p.setColor(new Color(rs.getInt(8),false));
            p.setColor(rs.getInt(9));
            p.setTimecreated(rs.getTimestamp(9).toLocalDateTime());
            results.add(p);
            
        }
        return results;
    }
    
    public boolean addElement(Police p) throws SQLException{
        PreparedStatement s = JDBCConnection.getConnection().prepareStatement("INSERT INTO Polices(NAME, DOB, POSX, POSY, SPEED, ATK, COLOR,TIMECREATED) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        s.setString(1,p.getName());
        s.setDate(2, new Date(p.getDob().getTime()));
        s.setInt(3,p.getPosx());
        s.setInt(4,p.getPosy());
        s.setInt(5,p.getSpeed());
        s.setInt(6,p.getAtk());
        s.setInt(7,p.getColor());
        s.setTimestamp(8,Timestamp.valueOf(p.getTimecreated()));
        
        return s.execute();
        
    }
    
    public boolean editElement(Police p) throws SQLException {
        PreparedStatement s = JDBCConnection.getConnection().prepareStatement("UPDATE Polices SET NAME = ?,DOB = ?,POSX = ?,POSY = ?,SPEED = ?,ATK = ?,COLOR = ? where ID = ? ");
        s.setString(1,p.getName());
        s.setDate(2,  convertUtilToSql(p.getDob()));
        s.setInt(3,p.getPosx());
        s.setInt(4,p.getPosy());
        s.setInt(5,p.getSpeed());
        s.setInt(6,p.getAtk());
        s.setInt(7,p.getColor());
        s.setInt(8,p.getId());
       
        return s.execute();
        
    }
    
    public boolean deleteElement(Police p) throws SQLException {
        PreparedStatement s = JDBCConnection.getConnection().prepareStatement("DELETE FROM Polices where ID = ? ");
        s.setInt(1,p.getId());
        
        return s.execute();        
        
    }
    	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
                System.out.println(sDate);
		return sDate;
	}
    
}
