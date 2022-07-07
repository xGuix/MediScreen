package com.mediscreen.patient.controller;

import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
    void patientSearchTest() throws Exception
    {
        Patient patientsList;
        mockMvc.perform(get("/patient/search")
                        .param("firstName", "Guix"))
                .andExpect(status().isOk())
                .andExpect(view().name("patient"));
    }

    @Test
    void allPatientTest() throws Exception
    {
        mockMvc.perform(get("/allPatients"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("patientsList"))
                .andExpect(view().name("allPatients"));
    }
}
