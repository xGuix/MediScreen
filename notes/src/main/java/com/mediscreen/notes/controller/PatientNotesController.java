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
@RequestMapping("/note")
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Request list of patient notes to service
     * @param patientId Integer The patient id
     * @return ResponseEntity patientNotesList List of patient notes
     */
    @GetMapping("/patientNotes")
    public ResponseEntity<List<PatientNote>> getAllPatientNotesWithPatientId(@RequestParam Integer patientId){
        List<PatientNote> patientNotesList = patientNotesService.getAllPatientNotesWithPatientId(patientId);
        if (!patientNotesList.isEmpty()) {
            logger.info("Patient notes found: {}", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(patientNotesList);
        } else {
            logger.error("Patient notes not found: {}", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Request for adding patient note to service
     * @param newNote PatientNote The patient note
     * @return ResponseEntity PatientNote The patient notes added
     */
    @PostMapping(value = "/patientNotes")
    public ResponseEntity<PatientNote> addNewNote(@RequestBody PatientNote newNote) {
        return new ResponseEntity<>(patientNotesService.addNewNote(newNote), HttpStatus.CREATED);
    }

    /**
     * Request for updating patient note to service
     * @param updateNote PatientNote The patient note
     * @return ResponseEntity PatientNote The patient notes updated
     */
    @PutMapping("/patientNotes")
    public ResponseEntity<PatientNote> updateNote(@RequestBody PatientNote updateNote){
        return new ResponseEntity<>(patientNotesService.updateNote(updateNote), HttpStatus.OK);
    }

    /**
     * Request for updating patient note to service
     * @param id String The patient note to delete
     * @return ResponseEntity PatientNote HttpStatus.OK
     */
    @DeleteMapping("/patientNotes")
    public ResponseEntity<PatientNote> deleteNote(@RequestParam String id){
        patientNotesService.deleteNote(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}