package com.mediscreen.notes.controller;

import com.mediscreen.notes.dto.NoteFormDto;
import com.mediscreen.notes.model.PatientNote;
import com.mediscreen.notes.service.PatientNotesService;
import com.mediscreen.notes.utils.EntityConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CurlNoteController {
    private static Logger logger = LogManager.getLogger(CurlNoteController.class);

    private final PatientNotesService patientNotesService;
    private final EntityConverter entityConverter;

    @Autowired
    public CurlNoteController(PatientNotesService patientNotesService, EntityConverter entityConverter) {
        this.patientNotesService = patientNotesService;
        this.entityConverter = entityConverter;
    }

    /**
     * Request note to add
     * @param noteDto NoteFormDto the patient note
     * @return noteFormDto Note Dto entity
     */
    @PostMapping(value = "/patHistory/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteFormDto> addNewPatient(@Valid NoteFormDto noteDto) {
        logger.info("Add Note for patientId: {}, and content: {}", noteDto.getPatId(), noteDto.getE());
        PatientNote patientNote = entityConverter.dtoToPatientNote(noteDto);
        patientNotesService.addNewNote(patientNote.getNote(),patientNote.getPatientId());
        return ResponseEntity.status(HttpStatus.CREATED).body(noteDto);
    }
}