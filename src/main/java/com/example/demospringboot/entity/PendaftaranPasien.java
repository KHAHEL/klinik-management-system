package com.example.demospringboot.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "pendaftaran_pasien")
public class PendaftaranPasien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_daftar")
    private Long idDaftar;

    // ========== RELASI KE PASIEN ==========
    @ManyToOne
    @JoinColumn(name = "id_pengguna", referencedColumnName = "id_pengguna")
    private Pasien pasien;

    // ========== RELASI KE ADMIN (optional) ==========
    @ManyToOne
    @JoinColumn(name = "id_admin", referencedColumnName = "id_pengguna")
    private Admin adminPendaftar; // null jika pasien daftar sendiri

    private String poli;
    private LocalDate tglDaftar;
    private Integer noAntrian;
    private String status;

    public PendaftaranPasien() {}

    public PendaftaranPasien(Pasien pasien, Admin adminPendaftar,
                             String poli, LocalDate tglDaftar,
                             Integer noAntrian, String status) {
        this.pasien = pasien;
        this.adminPendaftar = adminPendaftar;
        this.poli = poli;
        this.tglDaftar = tglDaftar;
        this.noAntrian = noAntrian;
        this.status = status;
    }

    public Long getIdDaftar() { return idDaftar; }
    public Pasien getPasien() { return pasien; }
    public Admin getAdminPendaftar() { return adminPendaftar; }
    public String getPoli() { return poli; }
    public LocalDate getTglDaftar() { return tglDaftar; }
    public Integer getNoAntrian() { return noAntrian; }
    public String getStatus() { return status; }

    public void setIdDaftar(Long idDaftar) { this.idDaftar = idDaftar; }
    public void setPasien(Pasien pasien) { this.pasien = pasien; }
    public void setAdminPendaftar(Admin adminPendaftar) { this.adminPendaftar = adminPendaftar; }
    public void setPoli(String poli) { this.poli = poli; }
    public void setTglDaftar(LocalDate tglDaftar) { this.tglDaftar = tglDaftar; }
    public void setNoAntrian(Integer noAntrian) { this.noAntrian = noAntrian; }
    public void setStatus(String status) { this.status = status; }
}
