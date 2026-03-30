package com.scm.repository;

import com.scm.entity.Enrollment;
import com.scm.entity.Student;
import com.scm.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudent(Student student);
    List<Enrollment> findByCourse(Course course);
    boolean existsByStudentAndCourse(Student student, Course course);
}