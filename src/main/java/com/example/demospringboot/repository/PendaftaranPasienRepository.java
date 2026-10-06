package com.example.demospringboot.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demospringboot.entity.PendaftaranPasien;

@Repository
public interface PendaftaranPasienRepository extends JpaRepository<PendaftaranPasien, Long> {

       List<PendaftaranPasien> findByPoli(String poli);

       @Query("SELECT MAX(p.noAntrian) FROM PendaftaranPasien p " +
              "WHERE p.poli = :poli AND p.tglDaftar = :tgl")
       Integer getMaxAntrianByPoliAndTgl(@Param("poli") String poli,
                                          @Param("tgl") LocalDate tanggal);

       @Modifying
       @Transactional
       @Query("DELETE FROM PendaftaranPasien p WHERE p.pasien.idPengguna = :id")
       void deleteByPasienId(@Param("id") Long id);

}
