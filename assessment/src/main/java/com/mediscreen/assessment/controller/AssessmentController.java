package com.mediscreen.assessment.controller;

import com.mediscreen.assessment.model.Report;
import com.mediscreen.assessment.service.AssessmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller Class Assessment
 */
@RestController
@RequestMapping("/assessment")
public class AssessmentController {

    private static final Logger logger = LoggerFactory.getLogger(AssessmentController.class);

    private final AssessmentService assessmentService;

    /**
     * Constructor: instance of assessment controller
     * @param assessmentService Service assessment
     */
    @Autowired
    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    /**
     * Request report of patient to send service
     * @param patientId Long The patient id
     * @return ResponseEntity Report The patient report
     */
    @GetMapping("/report/id")
    public ResponseEntity<Report> getReportByPatientId(@RequestParam Long patientId) {
        logger.info("Request assessment for patient Id : {}", patientId);
        Report report = assessmentService.generateReport(patientId);
        if (report!=null) {
            logger.info("Report successfully found - code : {}", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(report);
        } else {
            logger.error("Report not found, error - code : {}", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Request report of patient service
     * @param patId Long The patient id
     * @return String ResponseEntity patient report
     */
    @PostMapping(value = "/id", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCurlReportById(Long patId) {
        Report report = assessmentService.generateReport(patId);
        if (report!=null) {
            logger.info("Report successfully found - code : {}", HttpStatus.OK);
            String reportResponse = "Patient: "+report.getPatient().getFirstName()+" "
                    +report.getPatient().getLastName()+" (age "+report.getAge()+ ") diabetes assessment is: "+report.getRiskLevel();
            return ResponseEntity.status(HttpStatus.OK).body(reportResponse);
        } else {
            logger.error("Report can't be send, Patient don't exist - code : {}", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}