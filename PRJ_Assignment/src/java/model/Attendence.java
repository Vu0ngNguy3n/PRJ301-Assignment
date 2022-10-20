/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author admin
 */
public class Attendence {
    private String attend;
    private Session session;
    private Student student;
    private Boolean status;
    private java.sql.Timestamp timerecord;


    public Attendence() {
    }

    public Attendence(String attend, Session session, Student student, Boolean status, Timestamp timerecord) {
        this.attend = attend;
        this.session = session;
        this.student = student;
        this.status = status;
        this.timerecord = timerecord;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

  

    public String getAttend() {
        return attend;
    }

    public void setAttend(String attend) {
        this.attend = attend;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    

    public Timestamp getTimerecord() {
        return timerecord;
    }

    public void setTimerecord(Timestamp timerecord) {
        this.timerecord = timerecord;
    }

   
    
}
