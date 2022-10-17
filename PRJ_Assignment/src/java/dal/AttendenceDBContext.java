/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attendence;
import model.Group;
import model.Session;
import model.Student;

/**
 *
 * @author admin
 */
public class AttendenceDBContext extends DBContext<Attendence> {

    public ArrayList<Attendence> getlistStudent(String sessionid) {
        ArrayList<Attendence> attendences = new ArrayList<>();
        try {
            String sql = "SELECT s.sid,s.sname,ses.gid,a.status,a.timerecord,a.attend\n"
                    + "FROM Student as s\n"
                    + "INNER JOIN Attendence as a ON s.sid = a.sid\n"
                    + "INNER JOIN [Session] as ses ON ses.sessionid = a.sessionid\n"
                    + "WHERE ses.sessionid = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, sessionid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendence a = new Attendence();
                a.setAttend(rs.getString("attend"));
                Student stu = new Student();
                Session ses = new Session();
                Group g = new Group();
                g.setGid(rs.getString("gid"));
                ses.setGroup(g);
                a.setSession(ses);

                stu.setSid(rs.getString("sid"));
                stu.setSname(rs.getString("sname"));
                a.setStudent(stu);
                a.setStatus(rs.getBoolean("status"));
                a.setTimerecord(rs.getDate("timerecord"));
                attendences.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendenceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return attendences;
    }

    public int getnum(String sessionid) {
        int number = 0;
        try {
            String sql = "SELECT COUNT(*) as number\n"
                    + "FROM Student as s\n"
                    + "INNER JOIN Attendence as a ON s.sid = a.sid\n"
                    + "INNER JOIN [Session] as ses ON ses.sessionid = a.sessionid\n"
                    + "WHERE ses.sessionid = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, sessionid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                number = rs.getInt("number");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendenceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return number;
    }

    @Override
    public void insert(Attendence model) {
    }

    @Override
    public void update(Attendence model) {
       try {
            String sql = "UPDATE [Attendence]\n"
                    + "   SET [status] = ?\n"
                    + "      ,[timerecord] = GetDATE()\n"
                    + " WHERE attend =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, model.isStatus());
            stm.setString(2, model.getAttend());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AttendenceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Attendence model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Attendence getStringId(String id) {
        return null;
    }

    @Override
    public Attendence get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Attendence> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
