package com.scm.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @Column(name = "course_name")
    @JsonProperty("courseName")
    private String courseName;

    @Column(name = "course_code")
    @JsonProperty("courseCode")
    private String courseCode;

    @Column(name = "credits")
    @JsonProperty("credits")
    private Integer credits;

    @Column(name = "faculty")
    @JsonProperty("faculty")
    private String faculty;

    @Column(name = "instructor")
    @JsonProperty("instructor")
    private String instructor;

    @Column(name = "category")
    @JsonProperty("category")
    private String category;

    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    @Column(name = "max_enrollment")
    @JsonProperty("maxEnrollment")
    private Integer maxEnrollment;

    @Column(name = "duration")
    @JsonProperty("duration")
    private Integer duration;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Enrollment> enrollments;

    public Course() {}

    public Course(Long id, String courseName, String courseCode,
                  Integer credits, String faculty, String instructor,
                  String category, String description,
                  Integer maxEnrollment, Integer duration,
                  List<Enrollment> enrollments) {
        this.id = id;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credits = credits;
        this.faculty = faculty;
        this.instructor = instructor;
        this.category = category;
        this.description = description;
        this.maxEnrollment = maxEnrollment;
        this.duration = duration;
        this.enrollments = enrollments;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public Integer getCredits() { return credits; }
    public void setCredits(Integer credits) { this.credits = credits; }

    public String getFaculty() { return faculty; }
    public void setFaculty(String faculty) { this.faculty = faculty; }

    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getMaxEnrollment() { return maxEnrollment; }
    public void setMaxEnrollment(Integer maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
    }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public List<Enrollment> getEnrollments() { return enrollments; }
    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    @Override
    public String toString() {
        return "Course{id=" + id + ", courseName=" + courseName
            + ", courseCode=" + courseCode + "}";
    }
}