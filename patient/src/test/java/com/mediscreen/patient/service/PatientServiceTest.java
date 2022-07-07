package com.mediscreen.patient.service;

import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.IPatientRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.ui.Model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ActiveProfiles("test")
public class PatientServiceTest {
    @MockBean
    IPatientRepository patientRepository;
    @Autowired
    PatientService patientService;
    static Patient patient;
    static List<Patient> patientsList = new ArrayList<>();
    static Model model;

    @BeforeAll
    static void setupTest(){
        patient = new Patient(1L,"Rosa","Bonheur", Date.valueOf("1966-12-31"), 'F',"1 Brookside St","100-222-3333");
        patientsList.add(patient);
    }

    @Test
    void findAllPatientsListTest()
    {
        Mockito.when(patientRepository.findAll()).thenReturn(patientsList);
        assertEquals(patientsList ,patientService.getAllPatients());
    }

    @Test
    void findPatientByIdTest()
    {
        Mockito.when(patientRepository.getReferenceById(1L)).thenReturn(patient);
        assertEquals(patient ,patientService.getPatientById(1L));
    }

    @Test
    void findPatientByNameTest()
    {
        model.addAttribute("patientFound", patientsList);
        Mockito.when(patientRepository.getByfirstName(patient.getFirstName())).thenReturn(patientsList);
        Mockito.when(patientRepository.getBylastName(patient.getLastName())).thenReturn(patientsList);

        assertEquals(patientsList ,patientService.getByPatientName(model ,"Rosa", ""));
    }
}
