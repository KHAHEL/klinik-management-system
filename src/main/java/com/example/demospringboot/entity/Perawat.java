package com.example.demospringboot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "perawat")
public class Perawat extends Pengguna{

    private String ruangKerja;
    private Integer gaji;

    public Perawat() {}

    public Perawat(String username, String password, String nama, String alamat,
                   String gender, Integer umur, String noTelp,
                   String ruangKerja, Integer gaji) {
        super(username, password, "perawat", nama, alamat, gender, umur, noTelp);
        this.ruangKerja = ruangKerja;
        this.gaji = gaji;
    }

    public String getRuangKerja() { return ruangKerja; }
    public void setRuangKerja(String ruangKerja) { this.ruangKerja = ruangKerja; }

    public Integer getGaji() { return gaji; }
    public void setGaji(Integer gaji) { this.gaji = gaji; }
}
