package com.example.demospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.example.demospringboot.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByIdPengguna(Long idPengguna);
    Optional<Admin> findByUsername(String username);
}
