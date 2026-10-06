package com.example.demospringboot.entity;


import jakarta.persistence.*;


@Entity
@Table(name = "pasien")
public class Pasien extends Pengguna {


private String riwayatMedis;
private String keluhan;


public Pasien() {}


public Pasien(String username, String password, String nama, String alamat,
String gender, Integer umur, String noTelp,
String riwayatMedis, String keluhan) {
super(username, password, "pasien", nama, alamat, gender, umur, noTelp);
this.riwayatMedis = riwayatMedis;
this.keluhan = keluhan;
}


public String getRiwayatMedis() { return riwayatMedis; }
public void setRiwayatMedis(String riwayatMedis) { this.riwayatMedis = riwayatMedis; }


public String getKeluhan() { return keluhan; }
public void setKeluhan(String keluhan) { this.keluhan = keluhan; }
}