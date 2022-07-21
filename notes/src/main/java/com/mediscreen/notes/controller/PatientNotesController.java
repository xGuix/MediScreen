package com.mediscreen.notes.controller;

import com.mediscreen.notes.model.PatientNote;
import com.mediscreen.notes.service.PatientNotesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Patient Notes controller
 */
@RestController
@RequestMapping("/notes")
public class PatientNotesController {

    private static Logger logger = LogManager.getLogger(PatientNotesController.class);

    private final PatientNotesService patientNotesService;

    /**
     * Patient Notes Constructor
     * Instance of PatientNotesService
     * @param patientNotesService Patient Notes Service
     */
    public PatientNotesController(PatientNotesService patientNotesService) {
        this.patientNotesService = patientNotesService;
    }

    /**
     * Request list of all patients notes to service
     * @return ResponseEntity patientsNotesList List of all patients notes
     */
    @GetMapping("/allPatientsNotes")
    public ResponseEntity<List<PatientNote>> getAllPatientNotes(){
        List<PatientNote> patientNotesList = patientNotesService.getAllPatientNote();
        if (!patientNotesList.isEmpty()) {
            logger.info("Patients notes found: {}", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(patientNotesList);
        } else {
            logger.error("Patients notes not found: {}", HttpStatus.NOT_FOUND);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Request list of patient notes to service
     * @param patientId Integer The patient id
     * @return ResponseEntity patientNotesList List of patient notes
     */
    @GetMapping("/patientNotes")
    public ResponseEntity<List<PatientNote>> getAllPatientNotesWithPatientId(@RequestParam Long patientId){
        List<PatientNote> patientNotesList = patientNotesService.getAllPatientNotesWithPatientId(patientId);
        logger.info("Patient notes found: {}", HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(patientNotesList);
    }

    /**
     * Request patient notes to service
     * @param id String The note id
     * @return ResponseEntity patientNotesList List of patient notes
     */
    @GetMapping("/patientNotes/id")
    public ResponseEntity<PatientNote> getPatientNoteWithId(@RequestParam String id){
        PatientNote patientNotesList = patientNotesService.getPatientNotesWithId(id);
        logger.info("Patient notes found: {}", HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(patientNotesList);
    }

    /**
     * Request for adding patient note to service
     * @param note String The patient note
     * @param patientId Long The patient id
     * @return ResponseEntity PatientNote The patient notes added
     */
    @PostMapping(value = "/patientNotes/add")
    public ResponseEntity<PatientNote> addNewNote(@RequestParam String note, @RequestParam Long patientId) {
        logger.info("Patient notes add: {}", HttpStatus.CREATED);
        return new ResponseEntity<>(patientNotesService.addNewNote(note,patientId), HttpStatus.CREATED);
    }

    /**
     * Request for updating patient note to service
     * @param updateNote PatientNote The patient note
     * @return ResponseEntity PatientNote The patient notes updated
     */
    @PutMapping("/patientNotes/update")
    public ResponseEntity<PatientNote> updateNote(@RequestBody PatientNote updateNote){
        logger.info("Patient notes update: {}", HttpStatus.OK);
        return new ResponseEntity<>(patientNotesService.updateNote(updateNote), HttpStatus.OK);
    }

    /**
     * Request for updating patient note to service
     * @param id String The patient note to delete
     * @return ResponseEntity PatientNote HttpStatus.OK
     */
    @DeleteMapping(value="/patientNotes/delete")
    public ResponseEntity<PatientNote> deleteNote(@RequestParam String id){
        logger.info("Patient notes delete: {}", HttpStatus.OK);
        patientNotesService.deleteNote(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}