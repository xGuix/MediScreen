package com.mediscreen.patient.proxy;

import com.mediscreen.patient.dto.PatientNoteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value="notes" , url="localhost:8081")
public interface NoteProxy {
    /**
     * Get All list of patients notes.
     */
    @RequestMapping("/allPatientsNotes")
    List<PatientNoteDto> getAllPatientNotes();

    /**
     * Get All list of patients notes.
     * @param patientId Integer the patient i
     */
    @RequestMapping("/patientNotes")
    List<PatientNoteDto> getAllPatientNotesWithPatientId(Integer patientId);

    /**
     * Add patient notes.
     * @param newNote PatientNoteDto Patient note to add
     */
    @RequestMapping("/patientNotes")
    PatientNoteDto addNewNote(PatientNoteDto newNote);

    /**
     * Update patient notes.
     * @param updateNote PatientNoteDto Patient note to update
     */
    @RequestMapping("/patientNotes")
    PatientNoteDto updateNote(PatientNoteDto updateNote);

    /**
     * Delete patient notes.
     * @param id String Patient id
     */
    @RequestMapping("/patientNotes")
    void deleteNote(String id);
}
