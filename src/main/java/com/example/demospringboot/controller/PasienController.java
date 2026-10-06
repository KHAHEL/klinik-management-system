package com.example.demospringboot.controller;

import com.example.demospringboot.entity.Pasien;

import com.example.demospringboot.service.PendaftaranPasienService;
import com.example.demospringboot.service.PasienService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/pasien")
public class PasienController {

    @Autowired private PasienService pasienService;
    @Autowired private PendaftaranPasienService pendaftaranPasienService;
    // ==========================
    // UTIL
    // ==========================

    private boolean ensureLogged(HttpSession session) {
        return session.getAttribute("logPasien") != null;
    }

    private String redirectLogin() {
        return "redirect:/login";
    }

    private Pasien getLogged(HttpSession session) {
        return (Pasien) session.getAttribute("logPasien");
    }

    // ==========================
    // REGISTER FORM
    // ==========================

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("pasienInfo", new Pasien());
        return "register";
    }

    // ==========================
    // DASHBOARD PASIEN
    // ==========================
// ==========================
//  HOME PASIEN TANPA PARAM (AUTO PAKAI ID LOGIN)
// ==========================
@GetMapping("/home")
public String pasienHomeAuto(HttpServletRequest request) {

    HttpSession session = request.getSession();
    if (!ensureLogged(session)) return redirectLogin();

    Pasien login = getLogged(session);

    // redirect ke endpoint yang pakai {id}
    return "redirect:/pasien/home/" + login.getIdPengguna();
}

    @GetMapping("/dashboardPasien")
    public String dashboard(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        if (!ensureLogged(session)) return redirectLogin();

        Pasien login = getLogged(session);

        model.addAttribute("logPasien", login);
        return "pasien/dashboardPasien";
    }

    // ==========================
    // ANTRIAN POLI
    // ==========================

    @GetMapping("/antrianPasien")
    public String antrianPasien(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        if (!ensureLogged(session)) return redirectLogin();

        Pasien login = getLogged(session);

        model.addAttribute("logPasien", login);
        model.addAttribute("pendaftaranList", pendaftaranPasienService.getAll());

        return "pasien/antrianPasien";
    }

    @PostMapping("/daftar-poli")
public String daftarPoli(
        @RequestParam("id_pasien") Long idPasien,
        @RequestParam("poli") String poli,
        @RequestParam("tglDaftar") String tglDaftar
) {
    pendaftaranPasienService.daftarPasien(
            idPasien,
            poli,
            LocalDate.parse(tglDaftar)
    );

    return "redirect:/pasien/antrianPasien?success=1";
}


    // ==========================
    // PROFIL PASIEN
    // ==========================

    @GetMapping("/profilPasien")
public String profilPasien(Model model, HttpServletRequest request) {

    HttpSession session = request.getSession();
    if (!ensureLogged(session)) return redirectLogin();

    Pasien login = getLogged(session);

    // ini yang awalnya cuma logPasien
    model.addAttribute("logPasien", login);

    // tambahin ini supaya Thymeleaf nggak null
    model.addAttribute("pasienRec", login);   // dipakai di tabel atas
    model.addAttribute("pasienInfo", login);  // dipakai di form edit

    return "pasien/profilPasien";
}

    // ==========================
    // DETAIL PASIEN (KHUSUS DIRI SENDIRI)
    // ==========================

    @GetMapping("/home/{id}")
    public String pasienGetRec(Model model,
                               @PathVariable("id") long id,
                               HttpServletRequest request) {

        HttpSession session = request.getSession();
        Pasien login = getLogged(session);

        if (login == null) return redirectLogin();
        if (!login.getIdPengguna().equals(id)) return "redirect:/pasien/dashboardPasien";

        Pasien pasienRec = pasienService.getPasienById(id);

        model.addAttribute("logPasien", login);
        model.addAttribute("pasienRec", pasienRec);
        model.addAttribute("pasienInfo", pasienRec);

        return "pasien/pasien";
    }

    // ==========================
    // CRUD PASIEN (DIRI SENDIRI)
    // ==========================

    @PostMapping(value = {"/submit", "/submit/{id}"}, params = {"add"})
    public String pasienAdd(@ModelAttribute("pasienInfo") Pasien pasienInfo) {
        pasienService.addPasien(pasienInfo);
        return "redirect:/pasien/dashboardPasien";
    }

    @PostMapping(value = "/submit/{id}", params = {"edit"})
public String pasienEdit(@ModelAttribute("pasienInfo") Pasien pasienInfo,
                         @PathVariable("id") Long id,
                         HttpServletRequest request) {

    pasienService.updatePasien(id, pasienInfo);

    Pasien updated = pasienService.getPasienById(id);
    request.getSession().setAttribute("logPasien", updated);

    return "redirect:/pasien/profilPasien";
}

@PostMapping(value = "/submit/{id}", params = {"delete"})
public String pasienDelete(@PathVariable("id") Long id) {
    pasienService.deletePasien(id);
    return "redirect:/logout";
}
}
