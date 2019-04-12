package com.csumb.Administrative.dto;

import java.util.List;

import org.springframework.data.annotation.Id;

public class StudentDTO {
    @Id
    private String id;
    private String name;
    private int grade;
    private List<String> preferredClasses;
    private List<Boolean> preferred;
    private String academy;
    private List<String> schedule;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the grade
     */
    public int getGrade() {
        return grade;
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(int grade) {
        this.grade = grade;
    }

    /**
     * @return the preferredClasses
     */
    public List<String> getPreferredClasses() {
        return preferredClasses;
    }

    /**
     * @param preferredClasses the preferredClasses to set
     */
    public void setPreferredClasses(List<String> preferredClasses) {
        this.preferredClasses = preferredClasses;
    }

    /**
     * @return the preferred
     */
    public List<Boolean> getPreferred() {
        return preferred;
    }

    /**
     * @param preferred the preferred to set
     */
    public void setPreferred(List<Boolean> preferred) {
        this.preferred = preferred;
    }

    /**
     * @return the academy
     */
    public String getAcademy() {
        return academy;
    }

    /**
     * @param academy the academy to set
     */
    public void setAcademy(String academy) {
        this.academy = academy;
    }

    /**
     * @return the schedule
     */
    public List<String> getSchedule() {
        return schedule;
    }

    /**
     * @param schedule the schedule to set
     */
    public void setSchedule(List<String> schedule) {
        this.schedule = schedule;
    }
}