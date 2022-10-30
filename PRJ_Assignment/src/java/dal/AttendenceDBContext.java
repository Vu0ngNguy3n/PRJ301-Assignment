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
import model.Lecturer;
import model.Room;
import model.Session;
import model.Student;
import model.TimeSlot;

/**
 *
 * @author admin
 */
public class AttendenceDBContext extends DBContext<Attendence> {

    public ArrayList<Attendence> getListAttendStudent(String sid, String gid) {
        ArrayList<Attendence> listattend = new ArrayList<>();
        try {
            String sql = "SELECT att.attend,ses.sessionid,ses.date,ses.slot\n"
                    + "                  ,ses.room,sup.lecturerid,ses.gid,att.status,att.timerecord\n"
                    + "                 FROM Attendence as att\n"
                    + "                   INNER JOIN [Session] as ses ON att.sessionid = ses.sessionid\n"
                    + "                   INNER JOIN Superviser as sup ON ses.gid = sup.gid\n"
                    + "                   WHERE sid = ? and ses.gid = ?\n"
                    + "				   order by date";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, sid);
            stm.setString(2, gid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendence a = new Attendence();
                a.setAttend(rs.getString("attend"));
                a.setStatus(rs.getBoolean("status"));
                a.setTimerecord(rs.getTimestamp("timerecord"));

                Session s = new Session();
                s.setSessionid(rs.getString("sessionid"));
                s.setDate(rs.getDate("date"));

                TimeSlot t = new TimeSlot();
                t.setSlot(rs.getInt("slot"));
                Room r = new Room();
                r.setRoom(rs.getString("room"));
                Lecturer l = new Lecturer();
                l.setLecturerid(rs.getString("lecturerid"));
                Group g = new Group();
                g.setGid(rs.getString("gid"));
                s.setGroup(g);
                s.setLecturer(l);
                s.setRoom(r);
                s.setSlot(t);

                a.setSession(s);

                listattend.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendenceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listattend;
    }

   
    public ArrayList<Attendence> getlistStudent(String sessionid) {
        ArrayList<Attendence> attendences = new ArrayList<>();
        try {
            String sql = "SELECT s.sid,s.sname,ses.gid,ses.date,a.status,a.timerecord,a.attend\n"
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
                ses.setDate(rs.getDate("date"));
                a.setSession(ses);

                stu.setSid(rs.getString("sid"));
                stu.setSname(rs.getString("sname"));
                a.setStudent(stu);
                a.setStatus(rs.getBoolean("status"));
                a.setTimerecord(rs.getTimestamp("timerecord"));
                attendences.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendenceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return attendences;
    }

    public ArrayList<Attendence> getListAttend(String groupid) {
        ArrayList<Attendence> attendeces = new ArrayList<>();
        try {
            String sql = "Select att.attend,ses.sessionid,ses.date\n"
                    + "                    ,stu.sid,stu.sname\n"
                    + "                    ,att.status,att.timerecord\n"
                    + "                    FROM Attendence as att\n"
                    + "                    INNER JOIN [Session] as ses ON att.sessionid = ses.sessionid\n"
                    + "                    INNER JOIN Student as stu ON att.sid = stu.sid\n"
                    + "                    INNER JOIN [Group] as g ON g.gid = ses.gid\n"
                    + "                    WHERE g.gid = ? \n"
                    + "                   ORDER BY ses.date ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, groupid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendence a = new Attendence();
                a.setAttend(rs.getString("attend"));
                a.setStatus(rs.getBoolean("status"));
                a.setTimerecord(rs.getTimestamp("timerecord"));

                Student stu = new Student();
                Session ses = new Session();

                stu.setSid(rs.getString("sid"));
                stu.setSname(rs.getString("sname"));
                a.setStudent(stu);

                ses.setSessionid(rs.getString("sessionid"));
                ses.setDate(rs.getDate("date"));
                a.setSession(ses);
                attendeces.add(a);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendenceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return attendeces;

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
                    + "      ,[timerecord] = CURRENT_TIMESTAMP\n"
                    + " WHERE attend =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, model.getStatus());
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
