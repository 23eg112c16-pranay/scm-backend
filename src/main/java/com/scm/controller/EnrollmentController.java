package com.scm.controller;

import com.scm.entity.Enrollment;
import com.scm.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/enrollments")
@CrossOrigin(origins = "https://scm-frontend-zikd.onrender.com")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<?> getAllEnrollments() {
        try {
            List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
            System.out.println("GET enrollments count: " + enrollments.size());
            return ResponseEntity.ok(enrollments);
        } catch (Exception e) {
            System.out.println("GET enrollments error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> enroll(@RequestBody Map<String, Object> request) {
        System.out.println("=== ENROLL REQUEST ===");
        System.out.println(request);
        System.out.println("======================");

        try {
            if (request.get("studentId") == null || request.get("courseId") == null) {
                return ResponseEntity.badRequest()
                    .body("Missing fields. Got: " + request.keySet());
            }

            Long studentId = Long.valueOf(request.get("studentId").toString());
            Long courseId  = Long.valueOf(request.get("courseId").toString());
            String semester = request.get("semester") != null
                ? request.get("semester").toString() : "N/A";

            System.out.println("studentId=" + studentId
                + " courseId=" + courseId
                + " semester=" + semester);

            Enrollment enrollment = enrollmentService
                .enrollStudent(studentId, courseId, semester);

            System.out.println("Enrollment created successfully");
            return ResponseEntity.ok(enrollment);

        } catch (Exception e) {
            System.out.println("=== ENROLL ERROR ===");
            e.printStackTrace();
            System.out.println("====================");
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEnrollment(
            @PathVariable Long id,
            @RequestBody Map<String, Object> request) {
        System.out.println("=== UPDATE ENROLLMENT " + id + " ===");
        System.out.println(request);

        try {
            String status   = request.get("status")   != null
                ? request.get("status").toString()   : null;
            String semester = request.get("semester") != null
                ? request.get("semester").toString() : null;

            Enrollment updated = enrollmentService
                .updateEnrollment(id, status, semester);

            System.out.println("Updated enrollment: " + updated.getId());
            return ResponseEntity.ok(updated);

        } catch (Exception e) {
            System.out.println("=== UPDATE ERROR ===");
            e.printStackTrace();
            System.out.println("====================");
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getByStudent(@PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(
                enrollmentService.getEnrollmentsByStudent(studentId)
            );
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getByCourse(@PathVariable Long courseId) {
        try {
            return ResponseEntity.ok(
                enrollmentService.getEnrollmentsByCourse(courseId)
            );
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEnrollment(@PathVariable Long id) {
        try {
            enrollmentService.deleteEnrollment(id);
            return ResponseEntity.ok("Enrollment removed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}