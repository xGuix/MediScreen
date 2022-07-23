package com.mediscreen.patient.repository;

import com.mediscreen.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository Patient Interface
 * Extends JpaRepository
 */
public interface IPatientRepository extends JpaRepository<Patient,Long> {
    /**
     * {@inheritDoc}
     */
    Patient getPatientById(Long id);

    /**
     * {@inheritDoc}
     */
    List<Patient> getByfirstName(String firstName);

    /**
     * {@inheritDoc}
     */
    List<Patient> getBylastName(String lastName);
}