/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class Lecturer {
    private String lecturerid;
    private String lecturername;

    public Lecturer() {
    }

    public Lecturer(String lecturerid, String lecturername) {
        this.lecturerid = lecturerid;
        this.lecturername = lecturername;
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
