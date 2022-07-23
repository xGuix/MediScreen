package com.mediscreen.assessment.service;

import com.mediscreen.assessment.dto.PatientDto;
import com.mediscreen.assessment.dto.PatientNoteDto;
import com.mediscreen.assessment.model.Report;
import com.mediscreen.assessment.proxy.NoteProxy;
import com.mediscreen.assessment.proxy.PatientProxy;
import com.mediscreen.assessment.utils.Calculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Service Test Assessment Class
 */
@SpringBootTest
public class AssessmentServiceTest {

    @Autowired
    AssessmentService assessmentService;

    @MockBean
    private PatientProxy patientProxy;

    @MockBean
    private NoteProxy noteProxy;

    @MockBean
    private Calculator calculator;

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void generateReport_ReturnNoneOver30yTest(int ints)
    {
        PatientDto patient = new PatientDto(1L, "test", "test", Date.valueOf("1985-12-25"), 'M',
                "666 demon Street", "555-555-555");
        List<PatientNoteDto> notes = new ArrayList<>();
        when(patientProxy.patientById(1L)).thenReturn(patient);
        when(noteProxy.getAllPatientNotesWithPatientId(1L)).thenReturn(notes);
        when(calculator.calculateAge(patient.getBirthdate())).thenReturn(35);
        when(calculator.calculateStringOccurrence(notes)).thenReturn(ints);
        Report report = assessmentService.generateReport(1L);
        assertEquals("NONE", report.getRiskLevel());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1,2})
    void generateReport_ReturnNoneUnder30MenTest(int ints)
    {
        PatientDto patient = new PatientDto(1L, "test", "test", Date.valueOf("2000-12-25"), 'M',
                "666 demon Street", "555-555-555");
        List<PatientNoteDto> notes = new ArrayList<>();
        when(patientProxy.patientById(1L)).thenReturn(patient);
        when(noteProxy.getAllPatientNotesWithPatientId(1L)).thenReturn(notes);
        when(calculator.calculateAge(patient.getBirthdate())).thenReturn(20);
        when(calculator.calculateStringOccurrence(notes)).thenReturn(ints);
        Report report = assessmentService.generateReport(1L);
        assertEquals("NONE", report.getRiskLevel());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1,2})
    void generateReport_ReturnNoneUnder30WomenTest(int ints)
    {
        PatientDto patient = new PatientDto(1L, "test", "test", Date.valueOf("2000-12-25"), 'F',
                "666 demon Street", "555-555-555");
        List<PatientNoteDto> notes = new ArrayList<>();
        when(patientProxy.patientById(1L)).thenReturn(patient);
        when(noteProxy.getAllPatientNotesWithPatientId(1L)).thenReturn(notes);
        when(calculator.calculateAge(patient.getBirthdate())).thenReturn(20);
        when(calculator.calculateStringOccurrence(notes)).thenReturn(ints);
        Report report = assessmentService.generateReport(1L);
        assertEquals("NONE", report.getRiskLevel());
    }

    @ParameterizedTest
    @ValueSource(ints = {2,3,4,5})
    void generateReport_ReturnBorderlineTest(int ints)
    {
        PatientDto patient = new PatientDto(1L, "test", "test", Date.valueOf("1985-12-25"), 'F',
                "666 demon Street", "555-555-555");
        List<PatientNoteDto> notes = new ArrayList<>();
        when(patientProxy.patientById(1L)).thenReturn(patient);
        when(noteProxy.getAllPatientNotesWithPatientId(1L)).thenReturn(notes);
        when(calculator.calculateAge(patient.getBirthdate())).thenReturn(35);
        when(calculator.calculateStringOccurrence(notes)).thenReturn(ints);
        Report report = assessmentService.generateReport(1L);
        assertEquals("BORDERLINE", report.getRiskLevel());
    }

    @ParameterizedTest
    @ValueSource(ints = {6,7})
    void generateReport_ReturnInDangerOver30yTest(int ints)
    {
        PatientDto patient = new PatientDto(1L, "test", "test", Date.valueOf("1985-12-25"), 'M',
                "666 demon Street", "555-555-555");
        List<PatientNoteDto> notes = new ArrayList<>();
        when(patientProxy.patientById(1L)).thenReturn(patient);
        when(noteProxy.getAllPatientNotesWithPatientId(1L)).thenReturn(notes);
        when(calculator.calculateAge(patient.getBirthdate())).thenReturn(35);
        when(calculator.calculateStringOccurrence(notes)).thenReturn(ints);
        Report report = assessmentService.generateReport(1L);
        assertEquals("IN_DANGER", report.getRiskLevel());
    }

    @ParameterizedTest
    @ValueSource(ints = {3,4})
    void generateReport_ReturnInDangerUnder30MenTest(int ints)
    {
        PatientDto patient = new PatientDto(1L, "test", "test", Date.valueOf("2000-12-25"), 'M',
                "666 demon Street", "555-555-555");
        List<PatientNoteDto> notes = new ArrayList<>();
        when(patientProxy.patientById(1L)).thenReturn(patient);
        when(noteProxy.getAllPatientNotesWithPatientId(1L)).thenReturn(notes);
        when(calculator.calculateAge(patient.getBirthdate())).thenReturn(20);
        when(calculator.calculateStringOccurrence(notes)).thenReturn(ints);
        Report report = assessmentService.generateReport(1L);
        assertEquals("IN_DANGER", report.getRiskLevel());
    }

    @ParameterizedTest
    @ValueSource(ints = {4,5,6})
    void generateReport_ReturnInDangerUnder30WomenTest(int ints)
    {
        PatientDto patient = new PatientDto(1L, "test", "test", Date.valueOf("2000-12-25"), 'F',
                "666 demon Street", "555-555-555");
        List<PatientNoteDto> notes = new ArrayList<>();
        when(patientProxy.patientById(1L)).thenReturn(patient);
        when(noteProxy.getAllPatientNotesWithPatientId(1L)).thenReturn(notes);
        when(calculator.calculateAge(patient.getBirthdate())).thenReturn(20);
        when(calculator.calculateStringOccurrence(notes)).thenReturn(ints);
        Report report = assessmentService.generateReport(1L);
        assertEquals("IN_DANGER", report.getRiskLevel());
    }

    @ParameterizedTest
    @ValueSource(ints = {8,9,10,11})
    void generateReport_ReturnEarlyOnSetOver30yTest(int ints)
    {
        PatientDto patient = new PatientDto(1L, "test", "test", Date.valueOf("1985-12-25"), 'M',
                "666 demon Street", "555-555-555");
        List<PatientNoteDto> notes = new ArrayList<>();
        when(patientProxy.patientById(1L)).thenReturn(patient);
        when(noteProxy.getAllPatientNotesWithPatientId(1L)).thenReturn(notes);
        when(calculator.calculateAge(patient.getBirthdate())).thenReturn(35);
        when(calculator.calculateStringOccurrence(notes)).thenReturn(ints);
        Report report = assessmentService.generateReport(1L);
        assertEquals("EARLY_ONSET", report.getRiskLevel());
    }

    @ParameterizedTest
    @ValueSource(ints = {5,6,7,8,9,10,11})
    void generateReport_ReturnEarlyOnSetUnder30MenTest(int ints)
    {
        PatientDto patient = new PatientDto(1L, "test", "test", Date.valueOf("2000-12-25"), 'M',
                "666 demon Street", "555-555-555");
        List<PatientNoteDto> notes = new ArrayList<>();
        when(patientProxy.patientById(1L)).thenReturn(patient);
        when(noteProxy.getAllPatientNotesWithPatientId(1L)).thenReturn(notes);
        when(calculator.calculateAge(patient.getBirthdate())).thenReturn(20);
        when(calculator.calculateStringOccurrence(notes)).thenReturn(ints);
        Report report = assessmentService.generateReport(1L);
        assertEquals("EARLY_ONSET", report.getRiskLevel());
    }

    @ParameterizedTest
    @ValueSource(ints = {7,8,9,10,11})
    void generateReport_ReturnEarlyOnSetUnder30WomenTest(int ints)
    {
        PatientDto patient = new PatientDto(1L, "test", "test", Date.valueOf("2000-12-25"), 'F',
                "666 demon Street", "555-555-555");
        List<PatientNoteDto> notes = new ArrayList<>();
        when(patientProxy.patientById(1L)).thenReturn(patient);
        when(noteProxy.getAllPatientNotesWithPatientId(1L)).thenReturn(notes);
        when(calculator.calculateAge(patient.getBirthdate())).thenReturn(20);
        when(calculator.calculateStringOccurrence(notes)).thenReturn(ints);
        Report report = assessmentService.generateReport(1L);
        assertEquals("EARLY_ONSET", report.getRiskLevel());
    }
}
