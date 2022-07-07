package com.mediscreen.patient.controller;

import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.PatientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  Main App Thymeleaf controller
 */
@Controller
public class PatientController {
    private static Logger logger = LogManager.getLogger(PatientController.class);

    @Autowired
    PatientService patientService;

    /**
     *  Request Index Controller
     * @return index
     */
    @RequestMapping("/")
    public String index() {
        logger.info("Get index");
        return "index";
    }

    /**
     * Request patient page
     * @return patient page
     */
    @GetMapping("/patient")
    public  String patient() {
        logger.info("Get patient page");
        return "patient";
    }

    /**
     * Request patient to get information
     * @return patient page
     */
    @GetMapping("/patient/search")
    public  String patientSearch(Model model, String firstName, String lastName) {
        logger.info("Send search patient named: {} {}", firstName, lastName);
        patientService.getByPatientName(model,firstName,lastName);
        return "patient";
    }

    /**
     * Request patient to update information
     * @return patient page
     */
    @PostMapping("/patient/update")
    public String patientUpdate(Model model, Patient patient) {
        logger.info("Send update to patient named: {} {}", patient.getFirstName(), patient.getLastName());
        patientService.patientUpdate(model, patient);
        return "patient";
    }

    /**
     * Request to delete patient
     * @return allPatients page
     */
    @RequestMapping("/patient/delete")
    public String patientDelete(Model model, Patient patient) {
        logger.info("Send patient to delete named: {} {}", patient.getFirstName(), patient.getLastName());
        patientService.patientDelete(model,patient);
        return "patient";
    }

    /**
     * Request All List of patients
     * @return allPatients page
     */
    @GetMapping("/allPatients")
    public String allPatients(Model model) {
        logger.info("Send service request for all patients list");
        List<Patient> allPatients = patientService.getAllPatients();
        model.addAttribute("patientsList", allPatients);
        return "allPatients";
    }

    /**
     * Request to add new patient
     * @return allPatients page
     */
    @PostMapping("/allPatients/add")
    public String patientAdd(Model model, Patient patient) {
        logger.info("Send new patient to add named: {} {}", patient.getFirstName(), patient.getLastName());
        patientService.patientAdd(model,patient);
        return "redirect:/allPatients";
    }
}
