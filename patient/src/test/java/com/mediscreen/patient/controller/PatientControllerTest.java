package com.mediscreen.patient.controller;

import com.mediscreen.patient.model.Patient;
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

    static Patient patient;

    @BeforeAll
    static void setupTest(){
        patient = new Patient(0L,"Guix","Debrens", Date.valueOf("2021-12-25"), 'M', "333 Heaven Street", "06-666-6666");
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
}