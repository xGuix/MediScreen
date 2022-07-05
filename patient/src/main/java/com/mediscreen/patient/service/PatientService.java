package com.mediscreen.patient.service;

import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.IPatientRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    private static Logger logger = LogManager.getLogger(PatientService.class);
    @Autowired
    IPatientRepository patientRepository;

    /**
     * Request All patient List
     * @return List Patient The patients list
     */
    public List<Patient> getAllPatients() {
        logger.info("Get all patients list");
        return patientRepository.findAll();
    }

    /**
     * Request All patient List
     * @return List Patient The patients list
     */
    public Patient getPatientById(Long id) {
        logger.info("Get a patients with id: {}", id);
        return patientRepository.getReferenceById(id);
    }

    /**
     * Request patient with name
     * @param model Model the model
     * @param firstName String the first name
     * @param lastName String the last name
     * @return List Patient The patients found list
     */
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

    /**
     * Request patient with name
     * @param model Model the model
     * @param newPatient Patient the new patient infos
     * @return The updated patient
     */
    public Patient patientUpdate(Model model, Patient newPatient) {
        patientRepository.saveAndFlush(newPatient);
        model.addAttribute("patientUpdate", "Patient info successfully update and saved");
        return newPatient;
    }
}