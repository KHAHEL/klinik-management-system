package com.example.demospringboot.controller;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demospringboot.service.PendaftaranPasienService;
import com.example.demospringboot.service.PasienService;

@Controller
@RequestMapping("/poli")
public class PendaftaranPasienController {


@Autowired private PasienService pasienService;
@Autowired private PendaftaranPasienService pendaftaranPasienService;


// === Admin melihat semua pasien terdaftar ===
@GetMapping("/admin/list")
public String listSemuaPasien(Model model) {
model.addAttribute("pasienList", pasienService.getAllPasien());
return "admin/pasien-semua";
}


// === Admin lihat berdasarkan poli ===
@GetMapping("/admin/poli/{poli}")
public String listPasienPoli(@PathVariable String poli, Model model) {
model.addAttribute("poli", poli);
model.addAttribute("pasienPoliList", pendaftaranPasienService.getByPoli(poli));
return "admin/pasien-per-poli";
}

@PostMapping("/pendaftaran-poli/update")
public String updatePendaftaran(
        @RequestParam Long idDaftar,
        @RequestParam Long id_pengguna,
        @RequestParam String poli,
        @RequestParam String tglDaftar
) {
    LocalDate tanggal = LocalDate.parse(tglDaftar);

    pendaftaranPasienService.update(idDaftar, id_pengguna, poli, tanggal);

    return "redirect:/admin/pendaftaran-poli?updated=1";
}

// === Admin daftar pasien ke poli ===
@PostMapping("/admin/daftar")
public String daftarPasienAdmin(
@RequestParam("id_pengguna") Long idPasien,
@RequestParam("poli") String poli,
@RequestParam("tglDaftar") String tglDaftar
) {
pendaftaranPasienService.daftarPasien(idPasien, poli, LocalDate.parse(tglDaftar));
return "redirect:/admin/pendaftaran-poli";
}
}