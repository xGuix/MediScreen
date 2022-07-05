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
     *
     * @return index
     */
    @RequestMapping("/")
    public String index() {
        logger.info("Get index");
        return "index";
    }

    /**
     * Request patient page
     *
     * @return String Greetings from TourGuide!
     */
    @GetMapping("/patient")
    public  String patient() {
        logger.info("Get patient index");
        return "patient";
    }

    /**
     * Request patient to get information
     *
     * @return String Greetings from TourGuide!
     */
    @GetMapping("/patient/search")
    public  String patientSearch(Model model, String firstName, String lastName) {
        logger.info("Send patient with name: {} {}", firstName, lastName);
        patientService.getByPatientName(model,firstName,lastName);
        return "patient";
    }

    /**
     * Request patient to update information
     *
     * @return String Greetings from TourGuide!
     */
    @PostMapping("/patient/update")
    public String patientUpdate(Model model, Patient patient) {
        logger.info("Send patient named: {} {}", patient.getFirstName(), patient.getLastName());
        patientService.patientUpdate(model, patient);
        return "patient";
    }

    /**
     * Request All List of patients
     *
     * @return getAllPatients
     */
    @GetMapping("/allPatients")
    public String allPatients(Model model) {
        logger.info("Get all patients list");
        List<Patient> allPatients = patientService.getAllPatients();
        model.addAttribute("patientsList", allPatients);
        return "allPatients";
    }
}
