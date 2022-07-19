package com.mediscreen.patient.proxy;

import com.mediscreen.patient.dto.PatientNoteDto;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value="notes" , url="localhost:8081")
public interface NoteProxy {
    /**
     * Get All list of patients notes.
     */
    @RequestMapping("/notes/allPatientsNotes")
    List<PatientNoteDto> getAllPatientNotes();

    /**
     * Get All list of patients notes.
     * @param patientId Integer the patient i
     */
    @RequestMapping("/notes/patientNotes")
    List<PatientNoteDto> getAllPatientNotesWithPatientId(@RequestParam("patientId") Integer patientId);

    /**
     * Add patient notes.
     * @param newNote PatientNoteDto Patient note to add
     */
    @RequestMapping("/notes/patientNotes/add")
    PatientNoteDto addNewNote(@RequestBody PatientNoteDto newNote);

    /**
     * Update patient notes.
     * @param updateNote PatientNoteDto Patient note to update
     */
    @RequestMapping("/notes/patientNotes/update")
    PatientNoteDto updateNote(@RequestBody PatientNoteDto updateNote);

    /**
     * Delete patient notes.
     * @param id String Patient id
     */
    @RequestMapping("/notes/patientNotes/delete")
    void deleteNote(String id);
}