package com.example.demospringboot.service;

import com.example.demospringboot.entity.Admin;
import com.example.demospringboot.entity.Pasien;
import com.example.demospringboot.entity.PendaftaranPasien;
import com.example.demospringboot.repository.AdminRepository;
import com.example.demospringboot.repository.PasienRepository;
import com.example.demospringboot.repository.PendaftaranPasienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PendaftaranPasienService {

    @Autowired
    private PendaftaranPasienRepository repo;

    @Autowired
    private PasienRepository pasienRepo;

    @Autowired
    private AdminRepository adminRepo;



    // ====================================================================================
    // PASIEN DAFTAR SENDIRI (adminPendaftar = null)
    // ====================================================================================
    public PendaftaranPasien daftarPasien(Long idPasien, String poli, LocalDate tglDaftar) {

        Pasien pasien = pasienRepo.findById(idPasien).orElse(null);
        if (pasien == null) return null;

        Integer max = repo.getMaxAntrianByPoliAndTgl(poli, tglDaftar);
        int next = (max == null) ? 1 : max + 1;

        PendaftaranPasien p = new PendaftaranPasien();
        p.setPasien(pasien);
        p.setAdminPendaftar(null); // pasien daftar sendiri
        p.setPoli(poli);
        p.setTglDaftar(tglDaftar);
        p.setNoAntrian(next);
        p.setStatus("MENUNGGU");

        return repo.save(p);
    }


    // ====================================================================================
    // ADMIN / PERAWAT MENDAFTARKAN PASIEN
    // ====================================================================================
    public PendaftaranPasien daftarManual(Long idAdmin, Long idPasien,
                                          String poli, LocalDate tanggal, String status) {

        Admin admin = adminRepo.findById(idAdmin).orElse(null);
        Pasien pasien = pasienRepo.findById(idPasien).orElse(null);

        if (pasien == null) return null;

        Integer max = repo.getMaxAntrianByPoliAndTgl(poli, tanggal);
        int next = (max == null) ? 1 : max + 1;

        PendaftaranPasien p = new PendaftaranPasien();
        p.setPasien(pasien);
        p.setAdminPendaftar(admin); // siapa yang mendaftarkan
        p.setPoli(poli);
        p.setTglDaftar(tanggal);
        p.setNoAntrian(next);
        p.setStatus(status);

        return repo.save(p);
    }



    // ====================================================================================
    // UPDATE MANUAL
    // ====================================================================================
    public PendaftaranPasien updateManual(Long idDaftar, String poli, LocalDate tanggal, String status) {

        PendaftaranPasien p = repo.findById(idDaftar).orElse(null);
        if (p == null) return null;

        p.setPoli(poli);
        p.setTglDaftar(tanggal);
        p.setStatus(status);

        return repo.save(p);
    }



    // ====================================================================================
    // UPDATE DATA DASAR (GANTI PASIEN + POLI)
    // ====================================================================================
    public PendaftaranPasien update(Long idDaftar, Long idPasien,
                                    String poli, LocalDate tanggal) {

        PendaftaranPasien p = repo.findById(idDaftar).orElse(null);
        if (p == null) return null;

        Pasien pasien = pasienRepo.findById(idPasien).orElse(null);
        if (pasien == null) return null;

        p.setPasien(pasien);
        p.setPoli(poli);
        p.setTglDaftar(tanggal);

        return repo.save(p);
    }



    // ====================================================================================
    // GETTER
    // ====================================================================================
    public List<PendaftaranPasien> getAll() {
        return repo.findAll();
    }

    public PendaftaranPasien getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<PendaftaranPasien> getByPoli(String poli) {
        return repo.findByPoli(poli);
    }


    // ====================================================================================
    // UPDATE STATUS
    // ====================================================================================
    public void updateStatus(Long idDaftar, String statusBaru) {
    PendaftaranPasien pp = repo.findById(idDaftar).orElse(null);
    if (pp != null) {
        pp.setStatus(statusBaru);
        repo.save(pp);
    }
}


    // ====================================================================================
    // DOKTER DEFAULT (hanya helper)
    // ====================================================================================
    public String getDefaultDokterNama(String poli) {
        return switch (poli.toLowerCase()) {
            case "gigi" -> "Dr. Gigi Sari";
            case "anak" -> "Dr. Anak Bima";
            case "umum" -> "Dr. Umum Jaya";
            default -> "-";
        };
    }


// ====================================================================================
// DELETE PENDAFTARAN PASIEN
// ====================================================================================
public void delete(Long idDaftar) {
    repo.deleteById(idDaftar);
}

}
