package com.mediscreen.notes.repository;

import com.mediscreen.notes.model.PatientNote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientNotesRepository extends MongoRepository<PatientNote, String> {
        PatientNote getPatientNoteById(String id);
        List<PatientNote> getPatientNotesListByPatientId(Long patientId);
}