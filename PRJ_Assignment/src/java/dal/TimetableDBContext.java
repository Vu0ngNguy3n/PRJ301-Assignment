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
import model.TimeTable;

/**
 *
 * @author admin
 */
public class TimetableDBContext extends DBContext<TimeTable> {

    @Override
    public void insert(TimeTable model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(TimeTable model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(TimeTable model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public TimeTable get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<TimeTable> list(String s ) {
        ArrayList<TimeTable> list = new ArrayList<>();
        String sql = "Select a.attend,a.sessionid,a.[sid], a.status,a.timerecord,ses.slot,ses.room,ses.[date],g.subjectid,sup.lecturerid \n"
                + "From Attendence as a\n"
                + "inner join [Session] as ses on a.sessionid = ses.sessionid\n"
                + "inner join [Group] as g on ses.gid = g.gid\n"
                + "inner join [Superviser]	 as sup on sup.gid = g.gid\n"
                + "where sup.lecturerid = ?";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, s);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                TimeTable a = new TimeTable();
                String attend = rs.getString("attend");
                String sessionid = rs.getString("sessionid");
                String sid = rs.getString("sid");
                Boolean status = rs.getBoolean("status");
                java.sql.Date timerecord = rs.getDate("timerecord");
                int slot = rs.getInt("slot");
                String room = rs.getString("room");
                java.sql.Date date = rs.getDate("date");
                String subjectid = rs.getString("subjectid");
                String lecturerid = rs.getString("lecturerid");
                a.setAttend(attend);
                a.setSessionid(sessionid);
                a.setSid(sid);
                a.setStatus(status);
                a.setTimerecord(timerecord);
                a.setSlot(slot);
                a.setRoom(room);
                a.setDate(date);
                a.setSubjectid(subjectid);
                a.setLecturerid(lecturerid);
                list.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TimetableDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

}
