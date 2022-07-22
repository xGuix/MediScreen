package com.mediscreen.assessment.proxy;

import com.mediscreen.assessment.dto.PatientNoteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@FeignClient(value = "notes", url="${mediscreen.noteUrl}")
public interface NoteProxy {

    @GetMapping("/notes/patientNotes")
    List<PatientNoteDto> getAllPatientNotesWithPatientId(@RequestParam Long patientId);

    @GetMapping("/notes/patientNotes/id")
    PatientNoteDto getPatientNoteWithId(@RequestParam String id);
}