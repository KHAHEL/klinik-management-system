package com.example.demospringboot.controller;

import com.example.demospringboot.entity.Pasien;
import com.example.demospringboot.entity.PendaftaranPasien;
import com.example.demospringboot.entity.Perawat;
import com.example.demospringboot.service.PendaftaranPasienService;
import com.example.demospringboot.service.PasienService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/perawat")
public class PerawatController {

    @Autowired private PasienService pasienService;
    @Autowired private PendaftaranPasienService pendaftaranPasienService;

    // ==========================
    // UTIL
    // ==========================

    private boolean ensureLogged(HttpSession session) {
        return session.getAttribute("logPerawat") != null;
    }

    private String redirectLogin() {
        return "redirect:/login";
    }

    private Perawat getLogged(HttpSession session) {
        return (Perawat) session.getAttribute("logPerawat");
    }

    @GetMapping("/home")
public String home(Model model, HttpServletRequest request) {

    HttpSession session = request.getSession();
    if (!ensureLogged(session)) return redirectLogin();

    model.addAttribute("logPerawat", getLogged(session));
    model.addAttribute("pasienList", pasienService.getAllPasien());

    return "perawat/daftarPasien"; // buat file ini di templates/perawat/home.html
}


    // ==========================
    // DASHBOARD PERAWAT
    // ==========================

    @GetMapping("/dashboardPerawat")
    public String dashboardPerawat(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        if (!ensureLogged(session)) return redirectLogin();

        Perawat logged = getLogged(session);
        model.addAttribute("logPerawat", logged);

        return "perawat/dashboardPerawat";
    }

    // ==========================
    // DAFTAR PASIEN (READ-ONLY)
    // ==========================

    @GetMapping("/pasien")
    public String listPasien(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        if (!ensureLogged(session)) return redirectLogin();

        List<Pasien> pasienList = pasienService.getAllPasien();

        model.addAttribute("logPerawat", getLogged(session));
        model.addAttribute("pasienList", pasienList);

        return "perawat/daftarPasien";
    }

    // ==========================
    // DAFTAR PASIEN PER POLI
    // ==========================

    @GetMapping("/pasienPerPoli")
    public String listPasienPerPoli(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        if (!ensureLogged(session)) return redirectLogin();

        model.addAttribute("logPerawat", getLogged(session));
        model.addAttribute("pasienPoliList", pendaftaranPasienService.getAll());
        model.addAttribute("pasienList", pasienService.getAllPasien());

        return "perawat/pasienPerPoli";
    }

    @GetMapping("/pasienPerPoli/{id}")
public String getPendaftaranById(@PathVariable Long id,
                                 Model model,
                                 HttpServletRequest request) {

    HttpSession session = request.getSession();
    if (!ensureLogged(session)) return redirectLogin();

    PendaftaranPasien data = pendaftaranPasienService.getById(id);
    if (data == null) {
        return "redirect:/perawat/pasienPerPoli?error=notfound";
    }

    // Ambil langsung object pasien dari relasi
    Pasien pasien = data.getPasien();

    model.addAttribute("logPerawat", getLogged(session));
    model.addAttribute("pasienPoliList", pendaftaranPasienService.getAll());
    model.addAttribute("pasienPerPoliRec", data);
    model.addAttribute("pasienRec", pasien);

    return "perawat/pasienPerPoli";
}


    // ==========================
    // PROFIL PERAWAT
    // ==========================

    @GetMapping("/profilPerawat")
    public String profilPerawat(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        if (!ensureLogged(session)) return redirectLogin();

        Perawat logged = getLogged(session);

        model.addAttribute("logPerawat", logged);
        model.addAttribute("perawatRec", logged);

        return "perawat/profilPerawat";
    }

    @GetMapping("/jadwalPerawat")
    public String jadwalPerawat(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        if (!ensureLogged(session)) return redirectLogin();

        model.addAttribute("logPerawat", getLogged(session));

        return "perawat/jadwalPerawat"; // harus ada file ini
    }

    @PostMapping("/submitPerawat/{id}")
    public String updatePerawat(
            @PathVariable Long id,
            @ModelAttribute Perawat perawat,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        if (session.getAttribute("logPerawat") == null) {
            return "redirect:/login";
        }

        Perawat old = (Perawat) session.getAttribute("logPerawat");

        if (perawat.getPassword() == null || perawat.getPassword().isBlank()) {
            perawat.setPassword(old.getPassword());
        }

        session.setAttribute("logPerawat", perawat);

        return "redirect:/perawat/profilPerawat?success=1";
    }


}
