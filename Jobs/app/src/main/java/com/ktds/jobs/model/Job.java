package com.ktds.jobs.model;

import java.io.Serializable;

/**
 * Created by Admin on 2017-07-14.
 */

public class Job implements Serializable {

    private int id;
    private String company;
    private String title;
    private String location;
    private String jobType;
    private String jobCategory;
    private String level;
    private String requiredLevel;
    private String keyword;
    private String salary;

    @Override
    public String toString() {
        return super.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(String requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
