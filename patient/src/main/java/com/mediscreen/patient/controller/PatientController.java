package com.mediscreen.patient.controller;

import com.mediscreen.patient.dto.PatientNoteDto;
import com.mediscreen.patient.exception.PatientNotFoundException;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.NoteService;
import com.mediscreen.patient.service.PatientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  Patient Thymeleaf controller
 */
@Controller
public class PatientController {
    private static Logger logger = LogManager.getLogger(PatientController.class);

    @Autowired
    PatientService patientService;

    @Autowired
    NoteService noteService;

    Model model;

    /**
     *  Request Index Controller
     * @return index
     */
    @RequestMapping("/")
    public String index() {
        logger.info("Get homepage with quick search");
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
     * Request patient id page
     * @param id Long Patient id
     * @return the page of patient
     */
    @GetMapping("/patient/id")
    public  String patientPageInfo(Model model, Long id) throws PatientNotFoundException {
        logger.info("Get patient page with id: {}", id);
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patientFound", patient);
        getPatientNotes(model, id);
        return "patientPageInfo";
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
        return "redirect:/allPatients";
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

    /**
     * Request to find all patient notes
     * @param model Model note
     * @param patientId Integer patient id
     * @return patient page
     */
    @GetMapping("/patient/notes")
    public  String getPatientNotes(Model model, Long patientId) {
        logger.info("Send search notes for patient id: {}", patientId);
        List<PatientNoteDto> patientNoteList = noteService.getPatientNotes(patientId);
        model.addAttribute("PatientNoteList", patientNoteList);
        return "patientPageInfo";
    }

    /**
     * Request adding note to patient
     * @param newNote PatientNoteDto new note
     * @return patientPageInfo page
     */
    @PostMapping("/patient/notes/add")
    public String addPatientNote(PatientNoteDto newNote) {
        logger.info("Send new note: {}", newNote);
        noteService.addNewNote(newNote);
        return "redirect:/patientPageInfo";
    }

    /**
     * Request updating note to patient
     * @param updateNote PatientNoteDto updated note
     * @return patientPageInfo page
     */
    @RequestMapping(value="/patient/notes/update", method = {RequestMethod.GET, RequestMethod.PUT})
    public String updatePatientNote(PatientNoteDto updateNote) {
        logger.info("Send update note: {}", updateNote);
        noteService.updateNote(updateNote);
        return "redirect:/patientPageInfo";
    }

    /**
     * Request deleting note
     * @param id String note id
     * @return patientPageInfo page
     */
    @RequestMapping(value="/patient/notes/delete", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deletePatientNote(Model model,String id) throws PatientNotFoundException {
        logger.info("Send delete note id: {}", id);
        PatientNoteDto patientNote = noteService.getPatientNoteById(id);
        Long patientId = patientNote.getPatientId();
        noteService.deleteNote(id);
        return patientPageInfo(model, patientId);
    }
}