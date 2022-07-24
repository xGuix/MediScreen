package com.mediscreen.patient.proxy;

import com.mediscreen.patient.dto.PatientNoteDto;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Main patient-app
 * Interface Note Proxy
 */
@FeignClient(value="notes" , url="${mediscreen.microservice-notes}")
public interface NoteProxy {
    /**
     * Get All list of patients notes.
     * @return allPatientsNotes
     */
    @RequestMapping("/notes/allPatientsNotes")
    List<PatientNoteDto> getAllPatientsNotes();

    /**
     * Get All list of patients notes.
     * @param patientId Integer the patient id
     * @return allPatientsNotes
     */
    @RequestMapping("/notes/patientNotes")
    List<PatientNoteDto> getAllPatientNotesWithPatientId(@RequestParam("patientId") Long patientId);

    /**
     * Get patient note with id.
     * @param id String Note id
     * @return PatientNotes
     */
    @RequestMapping("/notes/patientNotes/id")
    PatientNoteDto getPatientNoteWithId(@RequestParam("id") String id);

    /**
     * Add patient notes.
     * @param note String Patient note to add
     * @param patientId Long The patient id
     * @return PatientNote
     */
    @PostMapping("/notes/patientNotes/add")
    PatientNoteDto addNewNote(@RequestParam("note") String note, @RequestParam("patientId") Long patientId);

    /**
     * Update patient notes.
     * @param updateNote PatientNoteDto Patient note to update
     * @return PatientNote
     */
    @PutMapping("/notes/patientNotes/update")
    PatientNoteDto updateNote(@RequestBody PatientNoteDto updateNote);

    /**
     * Delete patient notes.
     * @param id String Patient id
     */
    @DeleteMapping("/notes/patientNotes/delete")
    void deleteNote(@RequestParam("id")String id);
}