
package com.example.demospringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demospringboot.repository.PerawatRepository;
import com.example.demospringboot.entity.Perawat;

@Service
public class PerawatService {
@Autowired private PerawatRepository repo;


public List<Perawat> getAllPerawat() { return repo.findAll(); }
public Perawat getPerawatById(Long id) { return repo.findById(id).orElse(null); }


public Perawat updatePerawat(Long id, Perawat baru) {
return repo.findById(id)
.map(p -> {
p.setUsername(baru.getUsername());
p.setPassword(baru.getPassword());
p.setNama(baru.getNama());
p.setUmur(baru.getUmur());
p.setGender(baru.getGender());
p.setAlamat(baru.getAlamat());
p.setNoTelp(baru.getNoTelp());
p.setRuangKerja(baru.getRuangKerja());
p.setGaji(baru.getGaji());
return repo.save(p);
}).orElseThrow(() -> new RuntimeException("Perawat tidak ditemukan"));
}


public void deletePerawat(Long id) { repo.deleteById(id); }
public Perawat addPerawat(Perawat p) { return repo.save(p); }
}