package com.example.demospringboot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pengguna")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pengguna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pengguna")
    private Long idPengguna;

    private String username;
    private String password;
    private String role;
    private String nama;
    private String alamat;
    private String gender;
    private Integer umur;
    private String noTelp;

    public Pengguna() {}

    public Pengguna(String username, String password, String role,
                    String nama, String alamat, String gender,
                    Integer umur, String noTelp) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.nama = nama;
        this.alamat = alamat;
        this.gender = gender;
        this.umur = umur;
        this.noTelp = noTelp;
    }

    // getter & setter
    public Long getIdPengguna() { return idPengguna; }
    public void setIdPengguna(Long idPengguna) { this.idPengguna = idPengguna; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Integer getUmur() { return umur; }
    public void setUmur(Integer umur) { this.umur = umur; }

    public String getNoTelp() { return noTelp; }
    public void setNoTelp(String noTelp) { this.noTelp = noTelp; }
}
