package com.example.demospringboot.service;
import com.example.demospringboot.entity.Admin;
import com.example.demospringboot.entity.Dokter;
import com.example.demospringboot.entity.Perawat;
import com.example.demospringboot.entity.PendaftaranPasien;
import com.example.demospringboot.repository.DokterRepository;
import com.example.demospringboot.repository.PerawatRepository;
import com.example.demospringboot.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private DokterRepository dokterRepo;

    @Autowired
    private PerawatRepository perawatRepo;

    @Autowired
    private PendaftaranPasienService poliService;

    @Autowired
    private AdminRepository adminRepository;

    public List<Dokter> getAllDokter() {
        return dokterRepo.findAll();
    }

    public List<Perawat> getAllPerawat() {
        // sekarang ambil langsung dari tabel perawat
        return perawatRepo.findAll();
    }

    public List<PendaftaranPasien> getSemuaAntrian() {
        return poliService.getAll();
    }
    public Admin getByIdPengguna(Long idPengguna) {
    return adminRepository.findByIdPengguna(idPengguna);
}


}
