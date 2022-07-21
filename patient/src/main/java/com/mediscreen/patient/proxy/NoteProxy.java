package com.mediscreen.patient.proxy;

import com.mediscreen.patient.dto.PatientNoteDto;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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
    List<PatientNoteDto> getAllPatientNotesWithPatientId(@RequestParam("patientId") Long patientId);

    /**
     * Get patient note with id.
     */
    @RequestMapping("/notes/patientNotes/id")
    PatientNoteDto getPatientNoteWithId(@RequestParam("id") String id);

    /**
     * Add patient notes.
     * @param newNote PatientNoteDto Patient note to add
     */
    @PostMapping("/notes/patientNotes/add")
    PatientNoteDto addNewNote(@RequestBody PatientNoteDto newNote);

    /**
     * Update patient notes.
     * @param updateNote PatientNoteDto Patient note to update
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