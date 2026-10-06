package com.example.demospringboot.entity;


import jakarta.persistence.*;


@Entity
@Table(name = "dokter")
public class Dokter extends Pengguna {


private String spesialisasi;
@Column(name = "noRegis")
private String noRegis;
private Double gaji;
public Dokter() {}


public Dokter(String username, String password, String nama, String alamat,
String gender, Integer umur, String noTelp,
String spesialisasi, String noRegis, Double gaji) {
super(username, password, "dokter", nama, alamat, gender, umur, noTelp);
this.spesialisasi = spesialisasi;
this.noRegis = noRegis;
this.gaji = gaji;
}


public String getSpesialisasi() { return spesialisasi; }
public void setSpesialisasi(String spesialisasi) { this.spesialisasi = spesialisasi; }


public String getNoRegis() { return noRegis; }
public void setNoRegis(String noRegis) { this.noRegis = noRegis; }


public Double getGaji() { return gaji; }
public void setGaji(Double gaji) { this.gaji = gaji; }
}