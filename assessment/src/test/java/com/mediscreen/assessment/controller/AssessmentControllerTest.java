package com.mediscreen.assessment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.assessment.dto.PatientDto;
import com.mediscreen.assessment.model.Report;
import com.mediscreen.assessment.service.AssessmentService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Controller Test Class Assessment
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AssessmentController.class)
public class AssessmentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AssessmentService assessmentService;

    static Report patientReport;
    static PatientDto patient;

    @BeforeAll
    static void setupTest(){
        patient = new PatientDto(0L,"Guix","Debrens", Date.valueOf("2020-12-25"), 'M', "333 Heaven Street", "06-666-6666");
        patientReport = new Report(patient, 1, "NONE");
    }

    @Test
    void getReportByPatientId_returnReportTest() throws Exception
    {
        when(assessmentService.generateReport(any())).thenReturn(patientReport);
        mockMvc.perform(get("/assessment/report/id")
                        .param("patientId", objectMapper.writeValueAsString(patient.getId()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(patientReport)));
    }

    @Test
    void getReportByPatientId_returnNotFoundErrorTest() throws Exception
    {
        mockMvc.perform(get("/assessment/report/id")
                        .param("patientId", objectMapper.writeValueAsString(patient.getId()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}