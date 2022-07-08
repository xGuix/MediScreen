package com.mediscreen.patient.repository;

import com.mediscreen.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPatientRepository extends JpaRepository<Patient,Long> {
    Patient getPatientById(Long id);
    List<Patient> getByfirstName(String firstName);
    List<Patient> getBylastName(String lastName);
}
