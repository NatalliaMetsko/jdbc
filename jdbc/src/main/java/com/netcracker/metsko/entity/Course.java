package com.netcracker.metsko.entity;

import java.util.Objects;

public class Course {
    private long id;

    private String coursename;

    private String surname;

    private String name;

    private String fathername;

    private int hours;

    public Course() {
    }

    public Course(long id, String coursename, String surname, String name, String fathername, int hours) {
        this.id = id;
        this.coursename = coursename;
        this.surname = surname;
        this.name = name;
        this.fathername = fathername;
        this.hours = hours;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return getId() == course.getId() &&
                getHours() == course.getHours() &&
                Objects.equals(getCoursename(), course.getCoursename()) &&
                Objects.equals(getSurname(), course.getSurname()) &&
                Objects.equals(getName(), course.getName()) &&
                Objects.equals(getFathername(), course.getFathername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCoursename(), getSurname(), getName(), getFathername(), getHours());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Course{");
        sb.append("id=").append(id);
        sb.append(", coursename='").append(coursename).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", fathername='").append(fathername).append('\'');
        sb.append(", hours=").append(hours);
        sb.append('}');
        return sb.toString();
    }
}