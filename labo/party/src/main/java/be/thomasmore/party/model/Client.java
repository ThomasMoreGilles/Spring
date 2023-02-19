package be.thomasmore.party.model;

import java.time.LocalDate;

public class Client {
    private Long id;
    private String name;
    private LocalDate birthdate;
    private char gender;
    private LocalDate startdate;

    public Client(String name, LocalDate birthdate, char gender, LocalDate startdate) {
        this.name = name;
        this.birthdate = birthdate;
        setGender(gender);
        this.startdate = startdate;
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        if (gender != 'M' && gender != 'F') {
            throw new IllegalArgumentException("Gender should be either M or F");
        }
        this.gender = gender;
    }

    public LocalDate getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }
}

