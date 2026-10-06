package com.example.demospringboot.entity;


import jakarta.persistence.*;


@Entity
@Table(name = "admin")
public class Admin extends Pengguna{


private String department;
private String shift;
private String email;
private Double gaji;    


public Admin() {}


public Admin(String username, String password, String nama, String alamat,
String gender, Integer umur, String noTelp,
String department, String shift, String email, Double gaji) {
super(username, password, "admin", nama, alamat, gender, umur, noTelp);
this.department = department;
this.shift = shift;
this.email = email;
this.gaji = gaji;
}

public String getDepartment() { return department; }
public void setDepartment(String department) { this.department = department; }


public String getShift() { return shift; }
public void setShift(String shift) { this.shift = shift; }


public String getEmail() { return email; }
public void setEmail(String email) { this.email = email; }


public Double getGaji() { return gaji; }
public void setGaji(Double gaji) { this.gaji = gaji; }
}