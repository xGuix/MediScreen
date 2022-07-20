package com.mediscreen.patient.dto;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@DynamicUpdate
public class PatientNoteDto {

    @Id
    private String id;
    private Integer patientId;

    @NotBlank(message = "Notes cannot be blank!")
    private String note;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationDate;

    public PatientNoteDto()
    {}

    public PatientNoteDto(String id, Integer patientId, String note, Date creationDate) {
        this.id = id;
        this.patientId = patientId;
        this.note = note;
        this.creationDate = creationDate;
    }

    public PatientNoteDto(Integer patientId, String note) {
        this.patientId = patientId;
        this.note = note;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}