package com.mediscreen.patient.controller;

import com.mediscreen.patient.dto.PatientNoteDto;
import com.mediscreen.patient.dto.ReportDto;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.AssessmentService;
import com.mediscreen.patient.service.NoteService;
import com.mediscreen.patient.service.PatientService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PatientService patientService;

    @MockBean
    NoteService noteService;

    @MockBean
    AssessmentService assessmentService;

    static Patient patient;
    static PatientNoteDto patientNote;
    static ReportDto patientReport;

    @BeforeAll
    static void setupTest(){
        patient = new Patient(1L,"Guix","Debrens", Date.valueOf("1985-12-25"), 'M', "333 Heaven Street", "06-666-6666");
        patientNote = new PatientNoteDto("62d12c25191bcc3f11d08547", 1L, "Something that can be set as few notes for test", Date.valueOf("1985-12-25"));
        patientReport = new ReportDto(patient, 35,"IN_DANGER");
    }

    @Test
    void indexTest() throws Exception
    {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void patientIndexTest() throws Exception
    {
        mockMvc.perform(get("/patient"))
                .andExpect(status().isOk())
                .andExpect(view().name("patient"));
    }

    @Test
    void patientInfoPageTest() throws Exception
    {
        mockMvc.perform(get("/patient/id")
                .param("id", "0"))
                .andExpect(status().isOk())
                .andExpect(view().name("patientPageInfo"));
    }

    @Test
    void patientSearchTest() throws Exception
    {
        mockMvc.perform(get("/patient/search")
                        .param("firstName", "Guix"))
                .andExpect(status().isOk())
                .andExpect(view().name("patient"));
    }

    @Test
    void patientUpdateTest() throws Exception
    {
        mockMvc.perform(post("/patient/update")
                        .param("model", model().toString())
                        .param("patient", String.valueOf(patient)))
                .andExpect(status().isOk())
                .andExpect(view().name("patient"));
    }

    @Test
    void patientDeleteTest() throws Exception
    {
        mockMvc.perform(delete("/patient/delete")
                        .param("model", model().toString())
                        .param("patient", String.valueOf(patient)))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/allPatients"));
    }

    @Test
    void allPatientTest() throws Exception
    {
        mockMvc.perform(get("/allPatients"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("patientsList"))
                .andExpect(view().name("allPatients"));
    }

    @Test
    void patientAddTest() throws Exception
    {
        mockMvc.perform(post("/allPatients/add")
                        .param("model", model().toString())
                        .param("patient", String.valueOf(patient)))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/allPatients"));
    }

    @Test
    void getPatientNotesTest() throws Exception
    {
        mockMvc.perform(get("/patient/notes")
                        .param("patientId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("patientPageInfo"));
    }

    @Test
    void addPatientNoteTest() throws Exception
    {
        mockMvc.perform(post("/patient/notes/add")
                        .param("note", "Something that can be set as few notes for test")
                        .param("patientId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("patientPageInfo"));
    }

    @Test
    void updatePatientNoteTest() throws Exception
    {
        when(noteService.getPatientNoteById(any())).thenReturn(patientNote);
        when(noteService.updateNote(any())).thenReturn(patientNote);
        mockMvc.perform(post("/patient/notes/update")
                        .param("note", "Something that can be set as few notes for test")
                        .param("id", "62d12c25191bcc3f11d08547"))
                .andExpect(status().isOk())
                .andExpect(view().name("patientPageInfo"));
    }

    @Test
    void deletePatientNoteTest() throws Exception
    {
        when(noteService.getPatientNoteById(any())).thenReturn(patientNote);
        mockMvc.perform(delete("/patient/notes/delete")
                        .param("id", "62d12c25191bcc3f11d08547"))
                .andExpect(status().isOk())
                .andExpect(view().name("patientPageInfo"));
    }
}