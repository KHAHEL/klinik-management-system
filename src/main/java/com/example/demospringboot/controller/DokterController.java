package com.example.demospringboot.controller;

import com.example.demospringboot.entity.Dokter;
import com.example.demospringboot.entity.PendaftaranPasien;
import com.example.demospringboot.entity.Pengguna;
import com.example.demospringboot.service.DokterService;
import com.example.demospringboot.service.PendaftaranPasienService;
import com.example.demospringboot.service.PasienService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dokter")
public class DokterController {

    @Autowired private DokterService dokterService;
@Autowired private PendaftaranPasienService pendaftaranPasienService;

@Autowired private PasienService pasienService;
@Autowired private HttpSession session;


    // ==========================
    //  UTIL
    // ==========================

    private boolean ensureLogged() {
        return session.getAttribute("logDokter") != null;
    }

    private Pengguna getLoggedUser() {
        return (Pengguna) session.getAttribute("logDokter");
    }

    private String redirectLogin() {
        return "redirect:/login";
    }

    // ==========================
    //  MENU DOKTER
    // ==========================

    @GetMapping("/menu")
    public String menuDokter(Model model) {

        if (!ensureLogged()) return redirectLogin();

        Pengguna user = getLoggedUser();
        Dokter dokter = dokterService.getDokterByIdPengguna(user.getIdPengguna());

        model.addAttribute("pengguna", user);
        model.addAttribute("dokter", dokter);

        return "dokter/dokter-menu";
    }

    // ==========================
    //  PROFIL DOKTER
    // ==========================

    @GetMapping("/profil")
    public String profilDokter(Model model) {

        if (!ensureLogged()) return redirectLogin();

        Pengguna user = getLoggedUser();
        Dokter dokter = dokterService.getDokterByIdPengguna(user.getIdPengguna());

        model.addAttribute("pengguna", user);
        model.addAttribute("dokter", dokter);

        return "dokter/dokter-profil";
    }

    @GetMapping("/jadwal")
public String halamanJadwalDokter() {
    if (!ensureLogged()) return redirectLogin();
    return "dokter/jadwal"; // ini akan mencari templates/dokter/jadwal.html
}

@GetMapping("/profil/edit")
public String editProfilDokter(Model model) {

    if (!ensureLogged()) return redirectLogin();

    Pengguna user = getLoggedUser();
    Dokter dokter = dokterService.getDokterByIdPengguna(user.getIdPengguna());

    model.addAttribute("pengguna", user);
    model.addAttribute("dokter", dokter);

    return "dokter/dokter-profil-edit";
}


@PostMapping("/profil/update")
public String updateProfilDokter(
        @RequestParam String username,
        @RequestParam(required = false) String password,
        @RequestParam String spesialisasi,
        @RequestParam(required = false) Double gaji,
        Model model
) {
    if (!ensureLogged()) return redirectLogin();

    Pengguna sessionUser = getLoggedUser();

    // ambil data dokter berdasarkan ID pengguna (yang jadi PK dokter)
    Dokter dokter = dokterService.getDokterByIdPengguna(sessionUser.getIdPengguna());

    // UPDATE KE DOKTER TABLE
    dokter.setUsername(username);
    if (password != null && !password.isEmpty()) {
        dokter.setPassword(password);
    }
    dokter.setSpesialisasi(spesialisasi);
    dokter.setGaji(gaji);

    dokterService.save(dokter);

    // UPDATE JUGA DATA DI SESSION
    sessionUser.setUsername(username);
    if (password != null && !password.isEmpty()) {
        sessionUser.setPassword(password);
    }

    model.addAttribute("pengguna", sessionUser);
    model.addAttribute("dokter", dokter);
    model.addAttribute("success", "Profil berhasil diperbarui!");

    return "dokter/dokter-profil";
}



    // ==========================
    //  PASIEN PER POLI
    // ==========================

    @GetMapping("/pasien/poli/{poli}")
    public String listPasienPerPoli(@PathVariable String poli, Model model) {

        if (!ensureLogged()) return redirectLogin();

        List<PendaftaranPasien> daftar = pendaftaranPasienService.getByPoli(poli);

        model.addAttribute("poli", poli);
        model.addAttribute("pasienPoliList", daftar);

        return "dokter/pasien-per-poli";
    }

    // ==========================
    //  SEMUA PASIEN
    // ==========================

    @GetMapping("/pasien")
    public String listSemuaPasien(Model model) {

        if (!ensureLogged()) return redirectLogin();

        model.addAttribute("pasienList", pasienService.getAllPasien());
        return "dokter/pasien-semua";
    }
}
