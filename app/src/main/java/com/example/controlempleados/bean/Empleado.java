package com.example.controlempleados.bean;
import androidx.annotation.NonNull;

import java.io.Serializable;

public class Empleado implements Serializable {
    private int id;
    private String firstName;
    private String last_name;
    private String email;
    private String gender;
    private int phone;
    private String location;
    private String avatar;
    private String department;
    private String language;
    private String hiringDate;
    private String birthDate;


    public Empleado(int id, String firstName, String last_name, String email, String gender,
                    int phone, String location, String avatar, String department, String language,
                    String hiringDate, String birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.last_name = last_name;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.location = location;
        this.avatar = avatar;
        this.department = department;
        this.language = language;
        this.hiringDate = hiringDate;
        this.birthDate = birthDate;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(String hiringDate) {
        this.hiringDate = hiringDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @NonNull
    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                ", location='" + location + '\'' +
                ", avatar='" + avatar + '\'' +
                ", department='" + department + '\'' +
                ", language='" + language + '\'' +
                ", hiringDate='" + hiringDate + '\'' +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }
}
