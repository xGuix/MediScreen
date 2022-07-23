package com.mediscreen.patient.service;

import com.mediscreen.patient.dto.PatientNoteDto;
import com.mediscreen.patient.dto.ReportDto;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.proxy.AssessmentProxy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AssessmentServiceTest {

    @Autowired
    AssessmentService assessmentService;

    @MockBean
    AssessmentProxy assessmentProxy;

    static Patient patient;
    static ReportDto patientReport;


    @BeforeAll
    static void setupTest(){
        patient = new Patient(1L,"Guix","Debrens", Date.valueOf("1985-12-25"), 'M', "333 Heaven Street", "06-666-6666");
        patientReport = new ReportDto(patient,35,"IN_DANGER");
    }

    @Test
    void getReportByPatientIdTest()
    {
        Mockito.when(assessmentProxy.getReportByPatientId(patient.getId())).thenReturn(patientReport);
        ReportDto reportTest = assessmentService.getReportByPatientId(1L);
        assertEquals(patientReport , reportTest);
    }
}
