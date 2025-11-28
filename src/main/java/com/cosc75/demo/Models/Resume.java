package com.cosc75.demo.Models;

import java.util.List;

public class Resume {

    private PersonalInfo personalInfo;
    private ProfessionalSummary  professionalSummary;
    private List<Experience> experience;
    private Education education;
    private List<String> skills;

    public Resume() {}

    public PersonalInfo getPersonalInfo() { return personalInfo; }
    public void setPersonalInfo(PersonalInfo personalInfo) { this.personalInfo = personalInfo; }

    public ProfessionalSummary getSummary() { return professionalSummary; }
    public void setSummary(ProfessionalSummary summary) { this.professionalSummary = summary; }

    public List<Experience> getExperience() { return experience; }
    public void setExperience(List<Experience> experience) { this.experience = experience; }

    public Education getEducation() { return education; }
    public void setEducation(Education education) { this.education = education; }

    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }
}
