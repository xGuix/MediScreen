package com.mediscreen.patient.dto;

import com.mediscreen.patient.model.Patient;

public class ReportDto {
    private Patient patient;
    private int age;
    private String riskLevel;

    public ReportDto() {
    }

    public ReportDto(Patient patient, int age, String riskLevel) {
        this.patient = patient;
        this.age = age;
        this.riskLevel = riskLevel;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }
}