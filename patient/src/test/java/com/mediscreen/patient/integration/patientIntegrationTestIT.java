package com.mediscreen.patient.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.patient.controller.ApiController;
import com.mediscreen.patient.exception.PatientNotFoundException;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.IPatientRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import javax.inject.Inject;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class patientIntegrationTestIT{
    @Inject
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Inject
    ApiController patientController;
    @Autowired
    IPatientRepository patientRepository;

    static Patient patient;
    static Patient patient2;
    static List<Patient> patientList;
    @MockBean
    Model model;

    @BeforeAll
    static void setupTest(){
        patientList = new ArrayList<>();
        patient = new Patient(0L,"Guix","Debrens", Date.valueOf("0001-12-01"), 'M', "333 Heaven Street", "06-666-6666");
        patient2 = new Patient(1L,"Bob","Lazar", Date.valueOf("2022-02-02"), 'M', "51 Zone", "09-999-9999");
        patientList.add(patient);
    }

    @Test
    void getAllPatient() throws Exception
    {
        mockMvc.perform(get("/api/allPatients")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(patientList)));
    }

    @Test
    void addANewPatient() throws PatientNotFoundException {

        Patient patientToAdd = patientController.patientAdd(model, patient);
        Patient patientToCheck = patientRepository.getPatientById(patientToAdd.getId());

        assertEquals(patientToAdd,patientToCheck);
    }
}