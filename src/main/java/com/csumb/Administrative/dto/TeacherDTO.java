package com.csumb.Administrative.dto;

import org.springframework.data.annotation.Id;

public class TeacherDTO {
    @Id
    private String id;
    private String name;
    private String department;
    private int prep;

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
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return the prep
     */
    public int getPrep() {
        return prep;
    }

    /**
     * @param prep the prep to set
     */
    public void setPrep(int prep) {
        this.prep = prep;
    }
}