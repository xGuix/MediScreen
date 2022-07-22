package com.mediscreen.notes.service;

import com.mediscreen.notes.model.PatientNote;
import com.mediscreen.notes.repository.PatientNotesRepository;
import groovy.transform.Generated;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Patient Notes Service
 */
@Service
public class PatientNotesService {

    private static Logger logger = LogManager.getLogger(PatientNotesService.class);

    private final PatientNotesRepository patientNotesRepository;

    /**
     * Patient Notes Constructor
     * Instance of PatientNotesRepository
     * @param patientNotesRepository Patient Notes Repository
     */
    public PatientNotesService(PatientNotesRepository patientNotesRepository) {
        this.patientNotesRepository = patientNotesRepository;
    }

    /**
     * Request All List of patient notes to repository
     * @return patientNotesList List of all patient notes
     */
    @Generated
    public List<PatientNote> getAllPatientNote() {
        logger.info("Get all patients notes send to repository");
        return patientNotesRepository.findAll();
    }

    /**
     * Request list of patient notes to repository
     * @param patientId Integer The patient id
     * @return patientNotesList List of patient notes
     */
    public List<PatientNote> getAllPatientNotesWithPatientId(Long patientId) {
        logger.info("Get patient notes send to repository with id: {}", patientId);
        return patientNotesRepository.getPatientNotesListByPatientId(patientId);
    }

    /**
     * Request patient notes to repository
     * @param id String The note id
     * @return patientNotes patient notes
     */
    public PatientNote getPatientNotesWithId(String id) {
        logger.info("Get patient note send to repository with id: {}", id);
        return patientNotesRepository.getPatientNoteById(id);
    }

    /**
     * Request for adding patient note to repository
     * @param note String The patient note
     * @param patientId Long The patient id
     * @return PatientNote The patient notes added
     */
    public PatientNote addNewNote(String note, Long patientId) {
        logger.info("Add patient note send to repository with note: {}", note);
        PatientNote newPatientNote = new PatientNote();
        newPatientNote.setNote(note);
        newPatientNote.setPatientId(patientId);
        return patientNotesRepository.insert(newPatientNote);
    }

    /**
     * Request for updating patient note to repository
     * @param updateNote PatientNote The patient note
     * @return PatientNote The patient notes updated
     */
    public PatientNote updateNote(PatientNote updateNote) {
        logger.info("Update patient note send to repository with note: {}", updateNote);
        return patientNotesRepository.save(updateNote);
    }

    /**
     * Request for deleting patient note to repository
     * @param id String The patient id
     */
    public void deleteNote(String id) {
        patientNotesRepository.deleteById(id);
        logger.info("Deleted note with id: {}", id);
    }
}