package com.mediscreen.patient.dto;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

public class PatientDto {

    private Long id;
    @Length(max = 100)
    @NotEmpty(message = "Firstname is mandatory")
    private String given;
    @Length(max = 100)
    @NotEmpty(message = "Lastname is mandatory")
    private String family;
    @NotNull(message = "Birthdate is mandatory")
    private Date dob;
    @NotNull(message = "Gender is mandatory")
    private char sex;
    @Length(max = 300)
    @NotEmpty(message = "Address is mandatory")
    private String address;
    @Length(max = 20)
    @NotEmpty(message = "Phone number is mandatory")
    private String phone;

    public PatientDto(){}

    public PatientDto(Long id, String given, String family, Date dob, char sex, String address, String phone) {
        this.id = id;
        this.given = given;
        this.family = family;
        this.dob = dob;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGiven() {
        return given;
    }

    public void setGiven(String given) {
        this.given = given;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}