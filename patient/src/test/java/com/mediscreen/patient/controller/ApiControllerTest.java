package com.mediscreen.patient.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.NoteService;
import com.mediscreen.patient.service.PatientService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ApiController.class)
public class ApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    PatientService patientService;

    @MockBean
    NoteService noteService;

    static Patient patient;

    static List<Patient> patientList;

    static Model model;

    @BeforeAll
    static void setupTest(){
        patientList = new ArrayList<>();
        patient = new Patient(0L,"Guix","Debrens", Date.valueOf("2021-12-25"), 'M', "333 Heaven Street", "06-666-6666");
        patientList.add(patient);
    }

    @Test
    void allPatientsTest() throws Exception
    {
        when(patientService.getAllPatients()).thenReturn(patientList);
        mockMvc.perform(get("/api/allPatients")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(patientList)));
    }

    @Test
    void patientByNameTest() throws Exception
    {
        when(patientService.getByPatientName(any(),any(),any())).thenReturn(patientList);
        mockMvc.perform(get("/api/patient/search")
                        .param("firstName", "Guix")
                        .param("lastName", "Debrens"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(patientList)));
    }

    @Test
    void patientByIdTest() throws Exception
    {
        when(patientService.getPatientById(any())).thenReturn(patient);
        mockMvc.perform(get("/api/patient")
                        .param("id", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(patient)));
    }

    @Test
    void patientAddTest() throws Exception
    {
        when(patientService.patientAdd(any(),any())).thenReturn(patient);
        mockMvc.perform(post("/api/patient/add")
                        .param("model", String.valueOf(model))
                        .content(objectMapper.writeValueAsString(patient))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(patient)));
    }

    @Test
    void patientUpdateTest() throws Exception
    {
        when(patientService.patientUpdate(any(),any())).thenReturn(patient);
        mockMvc.perform(put("/api/patient/update")
                        .param("model", String.valueOf(model))
                        .content(objectMapper.writeValueAsString(patient))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(patient)));
    }

    @Test
    void patientDeleteTest() throws Exception
    {
        when(patientService.patientUpdate(any(),any())).thenReturn(patient);
        mockMvc.perform(delete("/api/patient/delete")
                        .param("model", String.valueOf(model))
                        .content(objectMapper.writeValueAsString(patient))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void patientDeleteByIdTest() throws Exception
    {
        mockMvc.perform(delete("/api/patient/deleteId")
                        .param("id", "0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}
