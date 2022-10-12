/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.group;

/**
 *
 * @author admin
 */
public class list extends DBContext<group>{

    @Override
    public ArrayList<group> list() {
        ArrayList<group> list = new ArrayList<>();
        String sql = "Select gid,subjectid from [Group]";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
            group g= new group();
            g.setGid(rs.getString("gid"));
            g.setSubid(rs.getString("subjectid"));
            list.add(g);}
        } catch (SQLException ex) {
            Logger.getLogger(list.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    public static void main(String[] args) {
        list l = new list();
        ArrayList<group> list = l.list();
        for (group object : list) {
            System.out.println(object.getGid() +" " + object.getSubid());
        }
    }
    
    
}
