package com.example.demospringboot.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.example.demospringboot.entity.Perawat;

@Repository
public interface PerawatRepository extends JpaRepository<Perawat, Long> {
    Optional<Perawat> findByUsername(String username);
}