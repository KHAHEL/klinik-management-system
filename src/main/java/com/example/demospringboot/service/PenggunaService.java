// package com.example.demospringboot.service;

// import com.example.demospringboot.entity.Pengguna;
// import com.example.demospringboot.repository.PenggunaRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class PenggunaService {

//     @Autowired
//     private PenggunaRepository penggunaRepository;

//     public Pengguna login(String username, String password) {

//         Optional<Pengguna> optional = penggunaRepository.findByUsername(username);

//         if (optional.isEmpty()) {
//             throw new RuntimeException("Username tidak ditemukan.");
//         }

//         Pengguna pengguna = optional.get();

//         if (!password.equals(pengguna.getPassword())) {
//             throw new RuntimeException("Password salah.");
//         }

//         return pengguna;
//     }
//     public List<Pengguna> getAllPengguna() {
//         return penggunaRepository.findAll();
//     }
//     public Optional<Pengguna> findByUsername(String username) {
//         return penggunaRepository.findByUsername(username);
//     }
//     public Pengguna getById(Long id) {
//         return penggunaRepository.findById(id)
//                 .orElseThrow(() -> new IllegalArgumentException(
//                         "Pengguna id " + id + " tidak ditemukan"
//                 ));
//     }
//     public Pengguna findById(Long id) {
//         return getById(id);
//     }
//     public void save(Pengguna pengguna) {
//         penggunaRepository.save(pengguna);
//     }
//     public void delete(Long idPengguna) {
//         penggunaRepository.deleteById(idPengguna);
//     }
//     public List<Pengguna> findByRole(String role) {
//         return penggunaRepository.findByRole(role);
//     }
// }
