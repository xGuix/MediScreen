package com.mediscreen.patient.controller;

import com.mediscreen.patient.exception.PatientNotFoundException;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.PatientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * Patient API controller
 */
@RestController
public class ApiController {
    private static Logger logger = LogManager.getLogger(ApiController.class);

    @Autowired
    PatientService patientService;

    /**
     * Request All List of patients
     * @return allPatients List of all patients
     */
    @GetMapping("/api/allPatients")
    public List<Patient> allPatients() {
        logger.info("Send service request for all patients list");
        List<Patient> allPatients = patientService.getAllPatients();
        return allPatients;
    }

    /**
     * Request patients search  by name
     * @param model Model attribute
     * @param firstName String patient first name
     * @param lastName String patient last name
     * @return patient list of matching search
     */
    @GetMapping("/api/patient/search")
    public Collection<Patient> patientsByName(Model model, String firstName, String lastName) {
        logger.info("Send search patient named: {} {}", firstName, lastName);
        Collection<Patient> patientsMatch = patientService.getByPatientName(model,firstName,lastName);
        return patientsMatch;
    }

    /**
     * Request patient find by id
     * @param id Long the patient id
     * @return patient The patient with id
     */
    @GetMapping("/api/patient")
    public Patient patientById(Long id) throws PatientNotFoundException {
        logger.info("Send search patient with id: {}", id);
        Patient patientById = patientService.getPatientById(id);
        return patientById;
    }

    /**
     * Request to add new patient
     * @param model Model attribute
     * @param patient Patient The patient to add
     * @return addPatient The patient added
     */
    @PostMapping("/api/patient/add")
    public Patient patientAdd(Model model, @RequestBody Patient patient) {
        logger.info("Send new patient to add named: {} {}", patient.getFirstName(), patient.getLastName());
        Patient addPatient = patientService.patientAdd(model,patient);
        return addPatient;
    }

    /**
     * Request to update patient information
     * @param model Model attribute
     * @param patient Patient The patient info to update
     * @return patientUpdate The patient to update
     */
    @PutMapping("/api/patient/update")
    public Patient patientUpdate(Model model, @RequestBody Patient patient) {
        logger.info("Send update to patient named: {} {}", patient.getFirstName(), patient.getLastName());
        Patient patientUpdate = patientService.patientUpdate(model, patient);
        return patientUpdate;
    }

    /**
     * Request to delete patient
     * @param model Model attribute
     * @param patient Patient to delete
     */
    @DeleteMapping("/api/patient/delete")
    public void patientDelete(Model model, @RequestBody Patient patient) {
        logger.info("Send patient to delete named: {} {}", patient.getFirstName(), patient.getLastName());
        patientService.patientDelete(model, patient);
    }

    /**
     * Request to delete patient by id
     * @param id Patient to delete
     */
    @DeleteMapping("/api/patient/deleteId")
    public void patientDelete(Long id) throws PatientNotFoundException {
        logger.info("Send patient id to delete: {}", id);
        patientService.deletePatientById(id);
    }
}