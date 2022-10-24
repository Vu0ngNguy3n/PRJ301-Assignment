/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Lecturer {
    private String lecturerid;
    private String lecturername;
    private String account;
    private ArrayList<Group> groups = new ArrayList<>();
    private ArrayList<Session> sessions = new ArrayList<>();

    public Lecturer() {
    }

    public Lecturer(String lecturerid, String lecturername, String account) {
        this.lecturerid = lecturerid;
        this.lecturername = lecturername;
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }
    
    

    public String getLecturerid() {
        return lecturerid;
    }

    public void setLecturerid(String lecturerid) {
        this.lecturerid = lecturerid;
    }

    public String getLecturername() {
        return lecturername;
    }

    public void setLecturername(String lecturername) {
        this.lecturername = lecturername;
    }
    
    
}
