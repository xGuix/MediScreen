package com.mediscreen.notes.model;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@DynamicUpdate
@Document(collection = "notes")
public class PatientNote implements Serializable {

    @Id
    private String id;
    private Long patientId;

    @NotBlank(message = "Notes cannot be blank!")
    private String note;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationDate = new Date();

    public PatientNote()
    {}

    public PatientNote(String id, Long patientId, String note, Date creationDate) {
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