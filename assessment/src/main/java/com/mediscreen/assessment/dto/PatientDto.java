package com.mediscreen.assessment.dto;

import groovy.transform.Generated;

import java.io.Serializable;
import java.sql.Date;

/**
 * Model PatientDto Class assessment-app
 * {@inheritDoc}
 */
@Generated
public class PatientDto implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthdate;
    private char gender;
    private String address;
    private String phoneNumber;

    /**
     * Default constructor patient
     */
    public PatientDto() {
    }

    /**
     * Full constructor patient
     * @param id Long patient id
     * @param firstName String patient first name
     * @param lastName String patient last name
     * @param birthdate Date patient birthdate
     * @param gender Char patient gender
     * @param address String patient address
     * @param phoneNumber String patient phone number
     */
    public PatientDto(Long id, String firstName, String lastName, Date birthdate, char gender, String address, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}