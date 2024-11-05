package com.skillspace.adminservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @Column(insertable = false)
    private int project_id;

    private long student_id;
    private long teacher_id;
    private String title;
    private String description;

    @Column(insertable = false)
    private int status_level;
    @Column(insertable = false)
    private String start_date;
    @Column(insertable = false)
    private String end_date;

    public int getStatus_level() {
        return status_level;
    }

    public void setStatus_level(int status_level) {
        this.status_level = status_level;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(long student_id) {
        this.student_id = student_id;
    }

    public long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(long teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Project{" +
                "project_id=" + project_id +
                ", student_id=" + student_id +
                ", teacher_id=" + teacher_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status_level=" + status_level +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                '}';
    }
}
