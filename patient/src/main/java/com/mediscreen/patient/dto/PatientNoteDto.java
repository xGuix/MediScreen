package com.mediscreen.patient.dto;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * Model PatientNoteDto Class patient-app
 * {@inheritDoc}
 */
@DynamicUpdate
public class PatientNoteDto {

    @Id
    private String id;
    private Long patientId;

    @NotBlank(message = "Notes cannot be blank!")
    private String note;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationDate;

    /**
     * Default constructor patient
     */
    public PatientNoteDto()
    {}

    /**
     * Full constructor patient
     * @param id String Note id
     * @param patientId Long patient id
     * @param note String the note
     * @param creationDate Date creation
     */
    public PatientNoteDto(String id, Long patientId, String note, Date creationDate) {
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