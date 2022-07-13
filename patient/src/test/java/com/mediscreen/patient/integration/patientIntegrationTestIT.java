//package com.mediscreen.patient.integration;
//
//import com.mediscreen.patient.controller.PatientController;
//import com.mediscreen.patient.model.Patient;
//import com.mediscreen.patient.repository.IPatientRepository;
//import com.mediscreen.patient.service.PatientService;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.mediscreen.patient.JsonTestMapper.asJsonString;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
//public class patientIntegrationTestIT{
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    static Patient patient;
//    static Patient patient2;
//    static List<Patient> patientList = new ArrayList<>();
//
//
//    @BeforeAll
//    static void setupTest(){
//        patient = new Patient(0L,"Guix","Debrens", Date.valueOf("0001-12-01"), 'M', "333 Heaven Street", "06-666-6666");
//        patient2 = new Patient(1L,"Bob","Lazar", Date.valueOf("2022-02-02"), 'M', "51 Zone", "09-999-9999");
//        patientList.add(patient);
//        patientList.add(patient2);
//    }
//
//    @Test
//    void patientInfoPageTest() throws Exception
//    {
//        mockMvc.perform(get("/api/allPatients")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().string(asJsonString(List.of(patientList))));
//    }
//}