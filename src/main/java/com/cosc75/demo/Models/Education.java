package com.cosc75.demo.Models;

import jakarta.persistence.Lob;

public class Education {
    @Lob
    private String primaryEducation;    // "School name, location, achievements..."
    @Lob
    private String secondaryEducation;  // "School name, location, achievements..."
    @Lob
    private String tertiaryEducation;   // "School name, location, achievements..."

    public String getTertiaryEducation() {
        return tertiaryEducation;
    }

    public void setTertiaryEducation(String tertiaryEducation) {
        this.tertiaryEducation = tertiaryEducation;
    }

    public String getSecondaryEducation() {
        return secondaryEducation;
    }

    public void setSecondaryEducation(String secondaryEducation) {
        this.secondaryEducation = secondaryEducation;
    }

    public String getPrimaryEducation() {
        return primaryEducation;
    }

    public void setPrimaryEducation(String primaryEducation) {
        this.primaryEducation = primaryEducation;
    }
}
