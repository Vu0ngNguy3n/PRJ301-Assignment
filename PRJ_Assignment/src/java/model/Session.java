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

    private String sessionid;
    private Boolean status;
    private TimeSlot slot;
    private Room room;
    private Date date;
    private Subject subject;
    private Lecturer lecturer;
    private Group group;
    ArrayList<Attendence> attendences = new ArrayList<>();

    public Session() {
    }

    public Session(String sessionid, Boolean status, TimeSlot slot, Room room, Date date, Subject subject, Lecturer lecturer, Group group) {
        this.sessionid = sessionid;
        this.status = status;
        this.slot = slot;
        this.room = room;
        this.date = date;
        this.subject = subject;
        this.lecturer = lecturer;
        this.group = group;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public TimeSlot getSlot() {
        return slot;
    }

    public void setSlot(TimeSlot slot) {
        this.slot = slot;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public ArrayList<Attendence> getAttendences() {
        return attendences;
    }

    public void setAttendences(ArrayList<Attendence> attendences) {
        this.attendences = attendences;
    }

    
}