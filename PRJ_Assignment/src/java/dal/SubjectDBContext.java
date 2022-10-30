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
import model.Subject;

/**
 *
 * @author admin
 */
public class SubjectDBContext extends DBContext<Subject> {

    public ArrayList<Subject> getListSubjectLec(String lecid) {
        ArrayList<Subject> subjects = new ArrayList<>();
        try {
            String sql = "SELECT sub.subjectid,sub.subjectname FROM [Subject] as sub\n"
                    + "INNER JOIN [Group] as g ON sub.subjectid = g.subjectid\n"
                    + "INNER JOIN Superviser as sup ON g.gid = sup.gid\n"
                    + "WHERE sup.lecturerid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lecid);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Subject s = new Subject();
                s.setSubjectid(rs.getString("subjectid"));
                s.setSubjectname(rs.getString("subjectname"));
                subjects.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subjects;
    }

  
    public String getgid(String subjectid) {
        try {
            String sql = "SELECT gid FROM  Subject as sub\n"
                    + "INNER JOIN [Group] as g ON sub.subjectid = g.subjectid\n"
                    + "WHERE sub.subjectid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, subjectid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString("gid");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void insert(Subject model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Subject model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Subject model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Subject getStringId(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Subject get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Subject> list() {
        ArrayList<Subject> subjects = new ArrayList<>();
        try {
            String sql = "SELECT subjectid,subjectname FROM Subject";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubjectid(rs.getString("subjectid"));
                s.setSubjectname(rs.getString("subjectname"));
                subjects.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subjects;
    }

}
