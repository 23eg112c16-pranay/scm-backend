package com.scm.service;

import com.scm.entity.Course;
import com.scm.entity.Enrollment;
import com.scm.entity.Student;
import com.scm.repository.CourseRepository;
import com.scm.repository.EnrollmentRepository;
import com.scm.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Enrollment enrollStudent(Long studentId, Long courseId, String semester) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student not found: " + studentId));
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new RuntimeException("Course not found: " + courseId));

        if (enrollmentRepository.existsByStudentAndCourse(student, course))
            throw new RuntimeException("Student already enrolled in this course");

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);    // ✅ fixed
        enrollment.setCourse(course);      // ✅ fixed
        enrollment.setSemester(semester);  // ✅ fixed
        enrollment.setStatus("ACTIVE");    // ✅ fixed
        return enrollmentRepository.save(enrollment);
    }

    public Enrollment updateEnrollment(Long id, String status, String semester) {
        Enrollment enrollment = enrollmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Enrollment not found: " + id));
        if (status   != null) enrollment.setStatus(status);    // ✅ fixed
        if (semester != null) enrollment.setSemester(semester); // ✅ fixed
        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> getEnrollmentsByStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student not found: " + studentId));
        return enrollmentRepository.findByStudent(student);
    }

    public List<Enrollment> getEnrollmentsByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new RuntimeException("Course not found: " + courseId));
        return enrollmentRepository.findByCourse(course);
    }

    public void deleteEnrollment(Long id) {
        if (!enrollmentRepository.existsById(id))
            throw new RuntimeException("Enrollment not found: " + id);
        enrollmentRepository.deleteById(id);
    }
}