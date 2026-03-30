package com.scm.service;

import com.scm.entity.Course;
import com.scm.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Course not found: " + id));
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course updated) {
        Course existing = courseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Course not found: " + id));
        existing.setCourseName(updated.getCourseName());
        existing.setCourseCode(updated.getCourseCode());
        existing.setCredits(updated.getCredits());
        existing.setFaculty(updated.getFaculty());
        existing.setInstructor(updated.getInstructor());
        existing.setCategory(updated.getCategory());
        existing.setDescription(updated.getDescription());
        existing.setMaxEnrollment(updated.getMaxEnrollment());
        existing.setDuration(updated.getDuration());
        return courseRepository.save(existing);
    }

    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id))
            throw new RuntimeException("Course not found: " + id);
        courseRepository.deleteById(id);
    }
}