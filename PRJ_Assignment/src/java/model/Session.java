/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Session {
     private String attend;
    private String sessionid;
    private String sid;
    private Boolean status;
    private Date timerecord;
    private int slot;
    private String room;
    private Date date;
    private String subjectid;
    private String lecturerid;
    ArrayList<Attendence> attendences = new ArrayList<>();
    

    public Session() {
    }

    public Session(String attend, String sessionid, String sid, Boolean status, Date timerecord, int slot, String room, Date date, String subjectid, String lecturerid) {
        this.attend = attend;
        this.sessionid = sessionid;
        this.sid = sid;
        this.status = status;
        this.timerecord = timerecord;
        this.slot = slot;
        this.room = room;
        this.date = date;
        this.subjectid = subjectid;
        this.lecturerid = lecturerid;
    }

    public ArrayList<Attendence> getAttendences() {
        return attendences;
    }

    public void setAttendences(ArrayList<Attendence> attendences) {
        this.attendences = attendences;
    }

    
  

   

    public String getAttend() {
        return attend;
    }

    public void setAttend(String attend) {
        this.attend = attend;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getTimerecord() {
        return timerecord;
    }

    public void setTimerecord(Date timerecord) {
        this.timerecord = timerecord;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    public String getLecturerid() {
        return lecturerid;
    }

    public void setLecturerid(String lecturerid) {
        this.lecturerid = lecturerid;
    }
    
}
