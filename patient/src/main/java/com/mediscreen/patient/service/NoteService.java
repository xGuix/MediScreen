package com.mediscreen.patient.service;

import com.mediscreen.patient.dto.PatientNoteDto;
import com.mediscreen.patient.proxy.NoteProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("NoteService")
public class NoteService {

    private static Logger logger = LogManager.getLogger(NoteService.class);

    @Autowired
    NoteProxy noteProxy;

    /**
     * Request all patients notes list
     * @return allPatientsNotesList PatientNoteDto The patients notes list
     */
    public List<PatientNoteDto> getAllPatientsNotes() {
        logger.info("Get all patients notes list");
        return noteProxy.getAllPatientNotes();
    }

    /**
     * Request patient notes list
     * @return patientNotes PatientNoteDto The patient notes list
     */
    public List<PatientNoteDto> getPatientNotes(Integer patientId) {
        logger.info("Get all patients notes list");
        return noteProxy.getAllPatientNotesWithPatientId(patientId);
    }
}
