package com.mediscreen.assessment.dto;

import groovy.transform.Generated;

import java.io.Serializable;
import java.util.Date;

@Generated
public class PatientNoteDto implements Serializable {

    private String id;
    private Long patientId;
    private String note;
    private Date creationDate;

    public PatientNoteDto()
    {}

    public PatientNoteDto(String id, Long patientId, String note, Date creationDate) {
        this.id = id;
        this.patientId = patientId;
        this.note = note;
        this.creationDate = creationDate;
    }

    public PatientNoteDto(Long patientId, String note) {
        this.patientId = patientId;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
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