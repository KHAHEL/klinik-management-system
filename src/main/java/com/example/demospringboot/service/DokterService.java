package com.example.demospringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demospringboot.entity.Dokter;
import com.example.demospringboot.repository.DokterRepository;

@Service
public class DokterService {
@Autowired private DokterRepository dokterRepo;


public List<Dokter> getAll() { return dokterRepo.findAll(); }
public Dokter getDokterById(Long id) { return dokterRepo.findById(id).orElse(null); }
public Dokter getDokterByIdPengguna(Long idPengguna) { return dokterRepo.findById(idPengguna).orElse(null); }
public Dokter save(Dokter dokter) { return dokterRepo.save(dokter); }
public void deleteDokter(Long id) { dokterRepo.deleteById(id); }
}