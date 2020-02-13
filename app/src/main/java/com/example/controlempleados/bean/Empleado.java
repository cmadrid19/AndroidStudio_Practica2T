package com.example.controlempleados.bean;

import android.net.Uri;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;

public class Empleado {
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
    private String nationlity;

    public Empleado(int id, String firstName, String last_name, String email, String gender,
                    int phone, String location, String avatar, String department, String language,
                    String hiringDate, String birthDate, String nationlity) {
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
        this.nationlity = nationlity;
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

    public String getNationlity() {
        return nationlity;
    }

    public void setNationlity(String nationlity) {
        this.nationlity = nationlity;
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
                ", nationlity='" + nationlity + '\'' +
                '}';
    }
}
