package com.mediscreen.assessment.model;

import com.mediscreen.assessment.dto.PatientDto;

public class Report {
    private PatientDto patient;
    private int age;
    private String riskLevel;

    public Report() {
    }

    public Report(PatientDto patient, int age, String riskLevel) {
        this.patient = patient;
        this.age = age;
        this.riskLevel = riskLevel;
    }

    public PatientDto getPatient() {
        return patient;
    }

    public void setPatient(PatientDto patient) {
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