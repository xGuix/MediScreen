package com.mediscreen.patient.service;

import com.mediscreen.patient.exception.PatientNotFoundException;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

/**
 * Service Patient Test Class
 */
@SpringBootTest
@ActiveProfiles("test")
public class PatientServiceTest {

    @Autowired
    PatientService patientService;

    @MockBean
    IPatientRepository patientRepository;

    @MockBean
    Model model;

    static Patient patient;
    static Patient patient2;
    static List<Patient> patientsList = new ArrayList<>();

    @BeforeAll
    static void setupTest(){
        patient = new Patient(1L,"Guix","Debrens", Date.valueOf("1985-12-25"), 'M', "333 Heaven Street", "06-666-6666");
        patient2 = new Patient(2L,"Rosa","Bonheur", Date.valueOf("1966-12-31"), 'F',"1 Brookside St","100-222-3333");
        patientsList.add(patient);
    }

    @Test
    void findAllPatientsListTest()
    {
        Mockito.when(patientRepository.findAll()).thenReturn(patientsList);
        List<Patient> patientListTest = patientService.getAllPatients();
        assertEquals(patientsList , patientListTest);
    }

    @Test
    void findPatientByIdTest() throws PatientNotFoundException
    {
        Mockito.when(patientRepository.getPatientById(1L)).thenReturn(patient);
        Patient patientTest = patientService.getPatientById(1L);
        assertEquals(patient , patientTest);
    }

    @Test
    void findPatientByIdReturnNotFoundExceptionTest() throws PatientNotFoundException
    {
        Mockito.when(patientRepository.getPatientById(2L)).thenReturn(null);
        assertThrows(PatientNotFoundException.class,() -> patientService.getPatientById(2L));
    }

    @Test
    void findPatientByNameWithFirstNameTest()
    {
        Mockito.when(patientRepository.getByfirstName(patient.getFirstName())).thenReturn(patientsList);
        Mockito.when(patientRepository.getBylastName(patient.getLastName())).thenReturn(null);

        List<Patient> patientListTest = patientService.getByPatientName(model,"Guix", "");

        assertEquals(patientsList.size() ,patientListTest.size());
    }

    @Test
    void findPatientByNameWithLastNameTest()
    {
        Mockito.when(patientRepository.getByfirstName(patient.getFirstName())).thenReturn(null);
        Mockito.when(patientRepository.getBylastName(patient.getLastName())).thenReturn(patientsList);

        List<Patient> patientListTest = patientService.getByPatientName(model, "", "Debrens");

        assertEquals(patientsList.size() ,patientListTest.size());
    }

    @Test
    void findPatientByNameWrongNameReturnNotFoundTest()
    {
        Mockito.when(patientRepository.getByfirstName(patient.getFirstName())).thenReturn(null);
        Mockito.when(patientRepository.getBylastName(patient.getLastName())).thenReturn(null);

        List<Patient> patientListTest = patientService.getByPatientName(model, "x", "x");

        assertEquals(0,patientListTest.size());
    }

    @Test
    void patientUpdateTest()
    {
        Patient patientUpdate = new Patient(0L,"G","D", Date.valueOf("0001-12-25"), 'T', "Address update test", "000-000-0000");
        Mockito.when(patientRepository.saveAndFlush(patient)).thenReturn(patientUpdate);
        Patient patientUpdated = patientService.patientUpdate(model, patientUpdate);
        assertEquals(patientUpdate , patientUpdated);
    }

    @Test
    void patientAddTest()
    {
        Mockito.when(patientRepository.saveAndFlush(patient)).thenReturn(patient2);
        Patient patientAdded = patientService.patientAdd(model, patient2);
        assertEquals(patient2 , patientAdded);
    }

    @Test
    void patientDeleteTest()
    {
        patientsList.add(patient2);
        patientService.patientDelete(model, patient2);

        assertTrue(patientsList.remove(patient2));
        verify(patientRepository, Mockito.times(1)).delete(patient2);
    }

    @Test
    void patientDeleteByIdTest() throws PatientNotFoundException {
        patientsList.add(patient2);
        Mockito.when(patientRepository.getPatientById(2L)).thenReturn(patient2);
        patientService.deletePatientById(patient2.getId());

        assertTrue(patientsList.remove(patient2));
        verify(patientRepository, Mockito.times(1)).getPatientById(patient2.getId());
        verify(patientRepository, Mockito.times(1)).delete(patient2);
    }

    @Test
    void patientDeleteByIdReturnPatientNotFoundExceptionTest() throws PatientNotFoundException {
        Mockito.when(patientRepository.getPatientById(2L)).thenReturn(null);
        assertThrows(PatientNotFoundException.class,() -> patientService.deletePatientById(2L));
    }
}
