package com.mediscreen.patient.service;

import com.mediscreen.patient.dto.PatientNoteDto;
import com.mediscreen.patient.dto.ReportDto;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.proxy.NoteProxy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

/**
 * Service Note Test Class patient-app
 */
@SpringBootTest
public class NoteServiceTest {
    @Autowired
    NoteService noteService;

    @MockBean
    NoteProxy noteProxy;

    static Patient patient;
    static PatientNoteDto patientNote;
    static List<PatientNoteDto> patientNotesList;
    static ReportDto patientReport;

    @BeforeAll
    static void setupTest(){
        patientNotesList = new ArrayList<>();
        patient = new Patient(1L,"Guix","Debrens", Date.valueOf("1985-12-25"), 'M', "333 Heaven Street", "06-666-6666");
        patientNote = new PatientNoteDto("62d12c25191bcc3f11d08547", 1L, "Something that can be set as few notes for test", Date.valueOf("1985-12-25"));
        patientReport = new ReportDto(patient,35,"IN_DANGER");
        patientNotesList.add(patientNote);
    }

    @Test
    void getAllPatientsNotesTest()
    {
        Mockito.when(noteProxy.getAllPatientsNotes()).thenReturn(patientNotesList);
        List<PatientNoteDto>  allPatientsList = noteService.getAllPatientsNotes();
        assertEquals(patientNotesList , allPatientsList);
    }

    @Test
    void getPatientNotesTest()
    {
        Mockito.when(noteProxy.getAllPatientNotesWithPatientId(any())).thenReturn(patientNotesList);
        List<PatientNoteDto>  patientNotesList = noteService.getPatientNotes(1L);
        assertEquals(patientNotesList , patientNotesList);
    }

    @Test
    void getPatientNoteByIdTest()
    {
        Mockito.when(noteProxy.getPatientNoteWithId(any())).thenReturn(patientNote);
        PatientNoteDto patientNoteTest = noteService.getPatientNoteById("62d12c25191bcc3f11d08547");
        assertEquals(patientNote , patientNoteTest);
    }

    @Test
    void addNewNoteTest()
    {
        Mockito.when(noteProxy.addNewNote(any(),any())).thenReturn(patientNote);
        PatientNoteDto patientNoteTest = noteService.addNewNote(patientNote.getNote(), patientNote.getPatientId());
        assertEquals(patientNote , patientNoteTest);
    }

    @Test
    void updateNoteTest()
    {
        Mockito.when(noteProxy.updateNote(any())).thenReturn(patientNote);
        PatientNoteDto patientNoteTest = noteService.updateNote(patientNote);
        assertEquals(patientNote , patientNoteTest);
    }

    @Test
    void deleteNoteTest()
    {
        PatientNoteDto noteToDelete;
        noteToDelete = new PatientNoteDto("DeleteTestWithStringId", 1L, "Something that can be set as few notes for test", Date.valueOf("1985-12-25"));
        patientNotesList.add(noteToDelete);

        noteService.deleteNote(noteToDelete.getId());
        assertTrue(patientNotesList.remove(noteToDelete));
        verify(noteProxy, Mockito.times(1)).deleteNote(noteToDelete.getId());
    }
}