package com.mediscreen.assessment.proxy;

import com.mediscreen.assessment.dto.PatientNoteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Assessment Interface Note Proxy
 */
@Service
@FeignClient(value = "notes", url= "localhost:8081" /*"${mediscreen.noteUrl}"*/)
public interface NoteProxy {

    /**
     * {@inheritDoc}
     */
    @GetMapping("/notes/patientNotes")
    List<PatientNoteDto> getAllPatientNotesWithPatientId(@RequestParam Long patientId);

    /**
     * {@inheritDoc}
     */
    @GetMapping("/notes/patientNotes/id")
    PatientNoteDto getPatientNoteWithId(@RequestParam String id);
}