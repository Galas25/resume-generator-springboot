package com.cosc75.demo.Models;

public class PersonalInfo {
    private String name;
    private String suffix;
    private String email;
    private String phone;
    private byte[] photo; // base64

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSuffix() { return suffix; }
    public void setSuffix(String suffix) { this.suffix = suffix; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public byte[] getPhoto() { return photo; }
    public void setPhoto(byte[] photo) { this.photo = photo; }
}
