/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import helper.DateTimeHelper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Group;
import model.Lecturer;
import model.Room;
import model.Session;
import model.Subject;
import model.TimeSlot;

/**
 *
 * @author admin
 */
public class SessionDBContext extends DBContext<Session> {

    public ArrayList<Session> listSession(String lecturerid, Date from, Date to) {
        ArrayList<Session> listSession = new ArrayList<>();
        try {
            String sql = "Select ses.sessionid,ses.date,ses.attend\n"
                    + ",lec.lecturerid,lec.lecturername\n"
                    + ",g.gid\n"
                    + ",r.room,r.building\n"
                    + ",sub.subjectid,sub.subjectname\n"
                    + ",t.slot,t.time\n"
                    + "from [Session] as ses \n"
                    + "INNER JOIN [Group] as g On ses.gid = g.gid \n"
                    + "INNER JOIN Superviser as sup ON g.gid = sup.gid\n"
                    + "INNER JOIN Lecturer as lec ON sup.lecturerid = lec.lecturerid\n"
                    + "INNER JOIN [Subject] as sub ON sub.subjectid = g.subjectid\n"
                    + "INNER JOIN TimeSlot as t ON t.slot = ses.slot \n"
                    + "INNER JOIN Room as r on r.room = ses.room\n"
                    + "WHERE lec.lecturerid = ? \n"
                    + "AND ses.date >= ?\n"
                    + "AND ses.date <= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lecturerid);
            stm.setDate(2, DateTimeHelper.toDateSql(from));
            stm.setDate(3, DateTimeHelper.toDateSql(to));
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session s = new Session();
                s.setSessionid(rs.getString("sessionid"));
                s.setDate(rs.getDate("date"));
                s.setStatus(rs.getBoolean("attend"));
                Lecturer lec = new Lecturer();
                Group group = new Group();
                Room room = new Room();
                Subject sub = new Subject();
                TimeSlot timeslot = new TimeSlot();

                lec.setLecturerid(rs.getString("lecturerid"));
                lec.setLecturername(rs.getString("lecturername"));
                s.setLecturer(lec);

                group.setGid(rs.getString("gid"));
                s.setGroup(group);

                room.setRoom(rs.getString("room"));
                room.setBuilding(rs.getString("building"));
                s.setRoom(room);

                sub.setSubjectid(rs.getString("subjectid"));
                sub.setSubjectname(rs.getString("subjectname"));
                s.setSubject(sub);

                timeslot.setSlot(rs.getInt("slot"));
                timeslot.setTime(rs.getString("time"));
                s.setSlot(timeslot);
                listSession.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSession;

    }

    public ArrayList<Session> ListSlot(String gid) {
        ArrayList<Session> sessions = new ArrayList<>();
        try {
            String sql = "SELECT sessionid,gid,date,room,slot,attend \n"
                    + "FROM [Session] \n"
                    + "WHERE  gid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, gid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session s = new Session();
                s.setSessionid(rs.getString("sessionid"));
                s.setDate(rs.getDate("date"));
                s.setStatus(rs.getBoolean("attend"));

                Group g = new Group();
                g.setGid(rs.getString("gid"));
                s.setGroup(g);

                Room r = new Room();
                r.setRoom(rs.getString("room"));
                s.setRoom(r);

                TimeSlot t = new TimeSlot();
                t.setSlot(rs.getInt("slot"));
                s.setSlot(t);
                sessions.add(s);

            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sessions;
    }

    public String getGid(String lecturerid) {
        
        try {
            String sql = "SELECT gid FROM Superviser\n"
                    + "WHERE lecturerid =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lecturerid);
            ResultSet rs = stm.executeQuery();
            return rs.getString("gid");
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void main(String[] args) {
        SessionDBContext s = new SessionDBContext();
        System.out.println(s.getGid("sonnt5"));
    }
    @Override
    public void insert(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Session get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Session> list() {

        return null;
    }

    @Override
    public Session getStringId(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
