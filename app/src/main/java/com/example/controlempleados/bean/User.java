package com.example.controlempleados.bean;

import androidx.annotation.NonNull;

public class User {

    private int id;
    private String email;
    private String name;
    private String password;
    private String birthDate;
    private char gender;
    private String nationality;

    public User(String email, String name, String password, String birthDate, char gender, String nationality) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.birthDate = birthDate;
        this.gender = gender;
        this.nationality = nationality;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", gender=" + gender +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
