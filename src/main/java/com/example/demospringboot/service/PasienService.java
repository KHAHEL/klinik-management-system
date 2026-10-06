package com.example.demospringboot.service;

import com.example.demospringboot.entity.Pasien;
import com.example.demospringboot.repository.PasienRepository;
import com.example.demospringboot.repository.PendaftaranPasienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasienService {

    @Autowired
    private PasienRepository pasienRepository;

    @Autowired
    private PendaftaranPasienRepository pendaftaranpasienRepository;

    // ==========================
    // REGISTER / ADD PASIEN
    // ==========================
    public Pasien registerPasien(Pasien pasien) {
        // cek username di tabel pasien saja
        if (pasienRepository.findByUsername(pasien.getUsername()).isPresent()) {
            throw new RuntimeException("Username sudah digunakan");
        }
        pasien.setRole("pasien");
        return pasienRepository.save(pasien);
    }

    public void addPasien(Pasien pasien) {
        registerPasien(pasien);
    }

    // ==========================
    // READ
    // ==========================
    public List<Pasien> getAllPasien() {
        return pasienRepository.findAll();
    }

    public Pasien getPasienById(Long id) {
        return pasienRepository.findById(id).orElse(null);
    }

    // alias utk kompatibilitas
    public Pasien getById(Long id) {
        return getPasienById(id);
    }

    // ==========================
    // UPDATE
    // ==========================
    public Pasien updatePasien(Long id, Pasien baru) {
        return pasienRepository.findById(id)
                .map(p -> {
                    p.setUsername(baru.getUsername());
                    p.setPassword(baru.getPassword());
                    p.setNama(baru.getNama());
                    p.setUmur(baru.getUmur());
                    p.setGender(baru.getGender());
                    p.setAlamat(baru.getAlamat());
                    p.setNoTelp(baru.getNoTelp());
                    p.setRiwayatMedis(baru.getRiwayatMedis());
                    p.setKeluhan(baru.getKeluhan());
                    return pasienRepository.save(p);
                })
                .orElseThrow(() -> new RuntimeException("Pasien tidak ditemukan"));
    }

    // ==========================
    // DELETE
    // ==========================
    public void deletePasien(Long id) {
        pendaftaranpasienRepository.deleteByPasienId(id);
        pasienRepository.deleteById(id);
    }
}
