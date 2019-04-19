package com.csumb.Administrative.entities;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.util.Pair;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document
public class Section extends Class {

    private int section_num;
    private int period_num;
    private List<Pair<String, String>> roster;
    private String teacherID;
    private int maxStudent;
    private String room;

    public Section() {
        super();
        this.section_num = -1;
        this.period_num = -1;
        this.roster = new ArrayList<>();
        this.teacherID ="";
        this.maxStudent = 30;
        this.room = "";
    }

    public Section(Class c, int section_num) {
        super(c);
        this.setId(this.getId() + "_" + section_num);
        this.section_num = section_num;
        this.teacherID = "";
        this.period_num = -1;
        this.maxStudent = 30;
        this.roster = new ArrayList<>();
        this.room ="";
    }

    public Section(Class c, int section_num, List<Pair<String, String>> students) {
        super(c);
        this.setId(this.getId() + "_" + section_num);
        this.section_num = section_num;
        this.roster = students;
        this.period_num = -1;
        this.maxStudent = 30;
        this.room ="";
    }


    public int getSection_num() {
        return section_num;
    }

    public void setSection_num(int section_num) {
        this.section_num = section_num;
    }

    public int getPeriod_num() {
        return period_num;
    }

    public void setPeriod_num(int period_num) {
        this.period_num = period_num;
    }

    public List<Pair<String, String>> getRoster() {
        return roster;
    }

    public void setRoster(List<Pair<String, String>> roster) {
        this.roster = roster;
    }

    public void addStudent(Student student) {
        this.roster.add(Pair.of(student.getId(),student.getName()));
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public int getMaxStudent() {
        return maxStudent;
    }

    public void setMaxStudent(int maxStudent) {
        this.maxStudent = maxStudent;
    }

    public boolean canAddStudent(){
        return maxStudent >= roster.size()+1;
    }

    public int getCurrentNumofStudents(){
        return roster.size();
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Section{" +
                "section_num=" + section_num +
                ", period_num=" + period_num +
                ", roster=" + roster +
                ", teacherID='" + teacherID + '\'' +
                ", maxStudent=" + maxStudent +
                ", room='" + room + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Section)) return false;
        if (!super.equals(o)) return false;
        Section section = (Section) o;
        return section_num == section.section_num &&
                period_num == section.period_num &&
                maxStudent == section.maxStudent &&
                roster.equals(section.roster) &&
                teacherID.equals(section.teacherID) &&
                room.equals(section.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), section_num, period_num, roster, teacherID, maxStudent, room);
    }
}