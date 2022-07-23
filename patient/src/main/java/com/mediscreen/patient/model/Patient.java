package com.mediscreen.patient.model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Model Patient Class  patient-app
 * {@inheritDoc}
 */
@Entity
@DynamicUpdate
@Table(name ="patients")
public class Patient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id", nullable = false)
    private Long id;
    @NotNull
    @Column(name="firstname")
    private String firstName;
    @NotNull
    @Column(name="lastname")
    private String lastName;
    @NotNull
    @Column(name="birthdate")
    private Date birthdate;
    @NotNull
    @Column(name="gender")
    private char gender;
    @NotNull
    @Column(name="address")
    private String address;
    @NotNull
    @Column(name="phone_number")
    private String phoneNumber;

    /**
     * Default constructor patient
     */
    public Patient() {
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
    public Patient(Long id, String firstName, String lastName, Date birthdate, char gender, String address, String phoneNumber) {
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