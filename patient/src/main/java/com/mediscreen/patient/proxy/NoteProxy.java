package com.mediscreen.patient.proxy;

import com.mediscreen.patient.dto.PatientNoteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value="note" , url="localhost:8081")
public interface NoteProxy {
    /**
     * Get All list of patients notes.
     * @return list of all patients notes
     */
    @RequestMapping("/allPatientsNotes")
    List<PatientNoteDto> getAllPatientNotes();

    /**
     * Get All list of patients notes.
     * @return list of all patients notes
     */
    @RequestMapping("/patientNotes")
    List<PatientNoteDto> getAllPatientNotesWithPatientId(Integer patientId);
}
