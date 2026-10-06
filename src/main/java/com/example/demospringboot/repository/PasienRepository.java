package com.example.demospringboot.repository;

import com.example.demospringboot.entity.Pasien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasienRepository extends JpaRepository<Pasien, Long> {
Optional<Pasien> findByUsername(String username);
}