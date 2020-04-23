package com.github.lightyear;

import java.util.List;
import java.util.Map;

public class Resume {
    private int id = 0;
    private String name = null;
    private String profession = null;

    private String phone = null;
    private String email = null;
    private String repository = null;
    private String region = null;

    private String universityTitle = null;
    private String faculty = null;

    private String company = null;
    private String time = null;
    private List<String> skills = null;

    public Resume resumeFactory(Map<String, Object> arguments){
        id = (Integer)arguments.get("id");
        name = (String)arguments.get("name");
        profession = (String)arguments.get("profession");
        phone = (String)arguments.get("phone");
        email = (String)arguments.get("email");
        repository = (String)arguments.get("repository");
        region = (String)arguments.get("region");
        universityTitle = (String)arguments.get("universityTitle");
        faculty = (String)arguments.get("faculty");
        company = (String)arguments.get("company");
        time = (String)arguments.get("time");
        skills = (List<String>)arguments.get("skillsList");

        return this;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProfession() {
        return profession;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getRepository() {
        return repository;
    }

    public String getRegion() {
        return region;
    }

    public String getUniversityTitle() {
        return universityTitle;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getCompany() {
        return company;
    }

    public String getTime() {
        return time;
    }

    public List getSkills() {
        return skills;
    }
}