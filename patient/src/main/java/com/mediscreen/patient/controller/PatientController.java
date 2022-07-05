package com.mediscreen.patient.controller;

import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.PatientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
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
        logger.info("Get patient index");
        return "index";
    }

    /**
     * Request patient to get information
     *
     * @return String Greetings from TourGuide!
     */
    @GetMapping("/patient")
    public  String patient(Model model, String firstName, String lastName) {
        logger.info("Get patient with name: {} {}", firstName, lastName);
        patientService.getByPatientName(model,firstName,lastName);
        return "index";
    }

    /**
     * Request patient to update information
     *
     * @return String Greetings from TourGuide!
     */
    @GetMapping("/patient/update")
    public  String patientUpdate(Model model, String firstName, String lastName, LocalDate birthdate,
                                 char gender, String address, String phoneNumber) {
        logger.info("Update patient with name: {} {}", firstName, lastName);
        patientService.updatePatient(model,firstName,lastName, birthdate, gender, address, phoneNumber);
        return "index";
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
