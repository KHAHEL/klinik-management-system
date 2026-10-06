package com.example.demospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.example.demospringboot.entity.Dokter;

@Repository
public interface DokterRepository extends JpaRepository<Dokter, Long> {
Dokter findByIdPengguna(Long idPengguna);
Optional<Dokter> findByUsername(String username);
}
