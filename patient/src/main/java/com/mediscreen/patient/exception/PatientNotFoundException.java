package com.mediscreen.patient.exception;

public class PatientNotFoundException extends Exception {
    private final String str;
    public PatientNotFoundException (String str){
        super(str);
        this.str = str;
    }
}