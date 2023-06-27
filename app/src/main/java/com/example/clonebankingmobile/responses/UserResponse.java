package com.example.clonebankingmobile.responses;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

public class UserResponse {
    private Long id;
    private String name;
    private String surname;
    private String thirdName;
    private Date birthday;
    private String phoneNumber;
    private String email;
    private Set<String> roles;

    public UserResponse(){}

    public UserResponse(Long id, String name, String surname, String thirdName, Date birthday, String phoneNumber, String email, Set<String> roles) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.thirdName = thirdName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
