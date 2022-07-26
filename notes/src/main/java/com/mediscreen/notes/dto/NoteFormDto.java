package com.mediscreen.notes.dto;

public class NoteFormDto {
    private String patId;
    private String e;

    public NoteFormDto() {

    }

    public NoteFormDto(String patId, String e) {
        this.patId = patId;
        this.e     = e;
    }

    public String getPatId() {
        return patId;
    }

    public void setPatId(String patId) {
        this.patId = patId;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }
}