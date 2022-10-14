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
import model.Lecturer;

/**
 *
 * @author admin
 */
public class LecturerDBContext extends DBContext<Lecturer>{

    @Override
    public void insert(Lecturer model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Lecturer model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Lecturer model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Lecturer get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Lecturer> list(String s) {
        ArrayList<Lecturer> list = new ArrayList<>();
        String sql = "SELECT lecturerid, lecturername FROM Lecturer";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Lecturer l = new Lecturer();
                String lecturerid = rs.getString("lecturerid");
                String lecturername = rs.getString("lecturername");
                l.setLecturerid(lecturerid);
                l.setLecturername(lecturername);
                list.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
