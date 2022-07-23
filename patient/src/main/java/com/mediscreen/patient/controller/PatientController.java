package com.mediscreen.patient.controller;

import com.mediscreen.patient.dto.PatientNoteDto;
import com.mediscreen.patient.dto.ReportDto;
import com.mediscreen.patient.exception.PatientNotFoundException;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.AssessmentService;
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
    @Autowired
    AssessmentService assessmentService;

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
        ReportDto patientReport = assessmentService.getReportByPatientId(id);
        model.addAttribute("patientReport", patientReport);
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
        model.addAttribute("PatientId", patientId);
        return "patientPageInfo";
    }

    /**
     * Request adding note to patient
     * @param note PatientNoteDto new note
     * @param patientId Long the patient id
     * @return patientPageInfo page
     */
    @RequestMapping(value="/patient/notes/add", method = {RequestMethod.GET, RequestMethod.POST})
    public String addPatientNote(Model model, String note, Long patientId) throws PatientNotFoundException {
        logger.info("Send new note: {} with patient id {}", note, patientId);
        noteService.addNewNote(note, patientId);
        return patientPageInfo(model, patientId);
    }

    /**
     * Request updating note to patient
     * @param note String updated note
     * @param id Long the note id
     * @return patientPageInfo page
     */
    @PostMapping("/patient/notes/update")
    public String updatePatientNote(Model model, String note, String id) throws PatientNotFoundException {
        logger.info("Send update note: {} with id {}", note, id);
        PatientNoteDto patientNote = noteService.getPatientNoteById(id);
        patientNote.setNote(note);
        Long patientId = patientNote.getPatientId();
        noteService.updateNote(patientNote);
        return patientPageInfo(model, patientId);
    }

    /**
     * Request deleting note
     * @param id String note id
     * @return patientPageInfo page
     */
    @RequestMapping(value="/patient/notes/delete", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deletePatientNote(Model model, String id) throws PatientNotFoundException {
        logger.info("Send delete note id: {}", id);
        PatientNoteDto patientNote = noteService.getPatientNoteById(id);
        Long patientId = patientNote.getPatientId();
        noteService.deleteNote(id);
        return patientPageInfo(model, patientId);
    }
}