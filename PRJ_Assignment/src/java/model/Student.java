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
public class Student {
    private String sid;
    private String sname;
    private boolean gender;
    private Date dob;
    private String hometown;
    private ArrayList<Group> groups = new ArrayList<>();
    private ArrayList<Attendence> attendeces = new ArrayList<>();

    public Student() {
    }

    public Student(String sid, String sname, boolean gender, Date dob, String hometown) {
        this.sid = sid;
        this.sname = sname;
        this.gender = gender;
        this.dob = dob;
        this.hometown = hometown;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public ArrayList<Attendence> getAttendeces() {
        return attendeces;
    }

    public void setAttendeces(ArrayList<Attendence> attendeces) {
        this.attendeces = attendeces;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }
    
}
