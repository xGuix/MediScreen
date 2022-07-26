package com.mediscreen.patient.utils;

import com.mediscreen.patient.dto.PatientFormDto;
import com.mediscreen.patient.model.Patient;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class EntityConverter {
    public Patient dtoToPatient(PatientFormDto p) {
        Patient patient = new Patient();
        patient.setFirstName(p.getGiven());
        patient.setLastName(p.getFamily());
        patient.setBirthdate(Date.valueOf(p.getDob()));
        patient.setGender(p.getSex().charAt(0));
        patient.setPhoneNumber(p.getPhone());
        patient.setAddress(p.getAddress());
        return patient;
    }
}