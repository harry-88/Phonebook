package com.atmostsoft.phonebook;

public class AccountInfo {
    private String name;
    private String number;
    private String email;
    private String dateOfBirth;
    private int pref;

    public AccountInfo(String name, String number) {
        this.name = name;
        this.number = number;
        pref = 1;
    }

    public AccountInfo(String name, String number, String email, String dateOfBirth) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        pref = 1;
    }

    public int getPref() {
        return pref;
    }

    public void setPref(int pref) {
        this.pref = pref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        String line = name+","+number+","+pref;
        if (email != null)
            line += ","+email;
        if (dateOfBirth != null)
            line += ","+dateOfBirth;
        return line;
    }
}
