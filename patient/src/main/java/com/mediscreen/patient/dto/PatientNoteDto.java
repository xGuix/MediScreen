package com.mediscreen.patient.dto;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class PatientNoteDto {

    @Id
    private String id;
    private Integer patientId;

    @NotBlank(message = "Notes cannot be blank!")
    private String note;
    private LocalDateTime creationDate;

    public PatientNoteDto()
    {}

    public PatientNoteDto(String id, Integer patientId, String note, LocalDateTime creationDate) {
        this.id = id;
        this.patientId = patientId;
        this.note = note;
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}