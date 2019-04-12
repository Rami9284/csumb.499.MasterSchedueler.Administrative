package com.csumb.Administrative.dto;

import java.util.List;

import org.springframework.data.util.Pair;

public class SectionDTO {
    private int section_num;
    private int period_num;
    private List<Pair<String, String>> roster;
    private String teacherID;
    private int maxStudent;

    /**
     * @return the section_num
     */
    public int getSection_num() {
        return section_num;
    }

    /**
     * @param section_num the section_num to set
     */
    public void setSection_num(int section_num) {
        this.section_num = section_num;
    }

    /**
     * @return the period_num
     */
    public int getPeriod_num() {
        return period_num;
    }

    /**
     * @param period_num the period_num to set
     */
    public void setPeriod_num(int period_num) {
        this.period_num = period_num;
    }

    /**
     * @return the roster
     */
    public List<Pair<String, String>> getRoster() {
        return roster;
    }

    /**
     * @param roster the roster to set
     */
    public void setRoster(List<Pair<String, String>> roster) {
        this.roster = roster;
    }

    /**
     * @return the teacherID
     */
    public String getTeacherID() {
        return teacherID;
    }

    /**
     * @param teacherID the teacherID to set
     */
    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    /**
     * @return the maxStudent
     */
    public int getMaxStudent() {
        return maxStudent;
    }

    /**
     * @param maxStudent the maxStudent to set
     */
    public void setMaxStudent(int maxStudent) {
        this.maxStudent = maxStudent;
    }
}