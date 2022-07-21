package com.mediscreen.notes.service;

import com.mediscreen.notes.model.PatientNote;
import com.mediscreen.notes.repository.PatientNotesRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ActiveProfiles("test")
public class PatientNoteServiceTest {

    @Autowired
    PatientNotesService patientNotesService;

    @MockBean
    PatientNotesRepository patientNotesRepository;

    static PatientNote patientNote;
    static PatientNote patientNote2;
    static List<PatientNote> patientNotesList = new ArrayList<>();

    @BeforeAll
    static void setupTest(){
        patientNote = new PatientNote("62d12c25191bcc3f11d08547", 1L, "Something that can be set as few notes for test", Date.valueOf("2001-12-25"));
        patientNote2 = new PatientNote("test000id000Patient56", 1L, "Something that can be change for test setting", Date.valueOf("2010-12-25"));
        patientNotesList.add(patientNote);
        patientNotesList.add(patientNote2);
    }

    @Test
    void getAllPatientNoteTest()
    {
        Mockito.when(patientNotesRepository.getPatientNotesListByPatientId(any())).thenReturn(patientNotesList);
        List<PatientNote> patientNoteListTest = patientNotesService.getAllPatientNotesWithPatientId(patientNote.getPatientId());
        assertEquals(patientNotesList , patientNoteListTest);
    }

    @Test
    void getPatientNotesWithId()
    {
        Mockito.when(patientNotesRepository.getPatientNoteById(any())).thenReturn(patientNote);
        PatientNote patientNoteTest = patientNotesService.getPatientNotesWithId(patientNote.getId());
        assertEquals(patientNote , patientNoteTest);
    }

    @Test
    void addNewNote()
    {
        PatientNote patientNote3;
        patientNote3 = new PatientNote("PourquoiPasCela", 1L, "Did it works? if you read this! Yes ;)", Date.valueOf("2010-12-25"));
        patientNotesList.add(patientNote3);
        Mockito.when(patientNotesRepository.insert(patientNote2)).thenReturn(patientNote2);
        PatientNote patientNoteTest = patientNotesService.addNewNote(patientNote2.getNote(),patientNote2.getPatientId());
        assertEquals(patientNote2 , patientNoteTest);
    }
}
