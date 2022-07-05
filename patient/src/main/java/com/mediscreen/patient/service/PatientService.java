package com.mediscreen.patient.service;

import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.IPatientRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    private static Logger logger = LogManager.getLogger(PatientService.class);
    @Autowired
    IPatientRepository patientRepository;
    public List<Patient> getAllPatients() {
        logger.info("Get all patients list");
        return patientRepository.findAll();
    }
    public List<Patient> getByPatientName(Model model, String firstName, String lastName) {
        logger.info("Get a patient by name: {} {}", firstName,lastName);
        List<Patient> patientsList = new ArrayList<>();
        List<Patient> firstNameList = patientRepository.getByfirstName(firstName);
        if (firstNameList.size()!=0) {
            for (Patient pfn : firstNameList){
                patientsList.add(pfn);
                model.addAttribute("patientFound", patientsList);
            }
        }
        List<Patient> lastNameList = patientRepository.getBylastName(lastName);
        if (lastNameList.size() != 0) {
            for (Patient pln : lastNameList){
                patientsList.add(pln);
                model.addAttribute("patientFound", patientsList);
            }
        }
        else if (firstNameList.size() == 0 && lastNameList.size() == 0){
            model.addAttribute("noPatientFound", "Patient does not exists in database. Try again.");
        }
        return patientsList;
    }
    public Patient updatePatient(Model model, String firstName, String lastName, LocalDate birthdate, char gender, String address, String phoneNumber) {
        List<Patient> patientsList = getByPatientName(model, firstName, lastName);
        Patient patient = patientsList.get(patientsList.size());
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setBirthdate(birthdate);
        patient.setGender(gender);
        patient.setAddress(address);
        patient.setPhoneNumber(phoneNumber);
        patientRepository.saveAndFlush(patient);
        return patient;
    }
}