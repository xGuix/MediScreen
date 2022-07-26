package com.mediscreen.patient.controller;

import com.mediscreen.patient.dto.PatientFormDto;
import com.mediscreen.patient.dto.ReportDto;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.AssessmentService;
import com.mediscreen.patient.service.PatientService;
import com.mediscreen.patient.utils.EntityConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CurlPatientController {

    private static Logger logger = LogManager.getLogger(CurlPatientController.class);

    private final PatientService patientService;
    private final AssessmentService assessmentService;
    private final EntityConverter entityConverter;

    @Autowired
    public CurlPatientController(PatientService patientService, AssessmentService assessmentService, EntityConverter entityConverter) {
        this.patientService = patientService;
        this.assessmentService = assessmentService;
        this.entityConverter = entityConverter;
    }

    /**
     * Request patient to add
     * @param patientFormDto PatientFormDto the patient
     * @param model Model default model
     * @return patient entity
     */
    @PostMapping(value = "/patient/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientFormDto> addNewPatient(@Valid PatientFormDto patientFormDto, Model model) {
        logger.info("Add patient named: {} {}", patientFormDto.getFamily(), patientFormDto.getGiven());
        patientService.patientAdd(model, entityConverter.dtoToPatient(patientFormDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(patientFormDto);
    }

    /**
     * Request report of patient id
     * @param patId Long The patient id
     * @return String ResponseEntity patient report
     */
    @PostMapping(value = "/assess/id", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCurlReportById(Long patId) {
        ReportDto report = assessmentService.getReportByPatientId(patId);
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

    /**
     * Request report of patient name
     * @param familyName String The patient name
     * @return String ResponseEntity patient report
     */
    @PostMapping(value = "/assess/familyName", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCurlReportByFamilyName(String familyName, Model model) {
        String lastName = familyName;
        List<Patient> patientList = patientService.getByPatientName(model,"" , lastName);
        Optional<Patient> patId = patientList.stream().findFirst();
        ReportDto report = assessmentService.getReportByPatientId(Long.valueOf(patId.hashCode()));
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