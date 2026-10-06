package com.example.demospringboot.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demospringboot.entity.Pasien;
import com.example.demospringboot.entity.PendaftaranPasien;
import com.example.demospringboot.entity.Pengguna;
import com.example.demospringboot.service.*;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private AdminService adminService;
    @Autowired private PendaftaranPasienService pendaftaranPasien;
    @Autowired private PasienService pasienService;
    @Autowired private DokterService dokterService;
    @Autowired private PerawatService perawatService;

    @Autowired private HttpSession session;

    private boolean ensureLogged() {
        return session.getAttribute("logAdmin") != null;
    }

    private Pengguna getLoggedUser() {
        return (Pengguna) session.getAttribute("logAdmin");
    }

    private String redirectLogin() {
        return "redirect:/login";
    }

    @GetMapping("/dokter")
    public String listDokter(Model model) {

        if (!ensureLogged()) return redirectLogin();

        model.addAttribute("dokterList", dokterService.getAll());
        return "admin/admin-dokter-list";
    }
    
    @GetMapping("/perawat")
    public String listPerawat(Model model) {

        if (!ensureLogged()) return redirectLogin();

        model.addAttribute("perawatList", perawatService.getAllPerawat());
        return "admin/admin-perawat-list";
    }

    @GetMapping({"", "/home", "/admin-menu"})
    public String home(Model model) {
        if (!ensureLogged()) return redirectLogin();

        Pengguna admin = getLoggedUser();

        model.addAttribute("pengguna", admin);
        model.addAttribute("dokterList", dokterService.getAll());
        model.addAttribute("perawatList", adminService.getAllPerawat());

        return "admin/admin-menu";
    }

    // =============================
    // CRUD PASIEN DARI ADMIN
    // =============================

    @GetMapping("/pendaftaran")
    public String pendaftaran(Model model) {
        if (!ensureLogged()) return redirectLogin();

        model.addAttribute("pendaftaranList", pendaftaranPasien.getAll());
        model.addAttribute("pasienInfo", new Pasien());

        return "admin/admin-pendaftaran-pasien";
    }

    @PostMapping("/pendaftaran-pasien")
    public String tambahPasienDariAdmin(@ModelAttribute Pasien pasien) {
        pasien.setRole("pasien");
        pasienService.addPasien(pasien);
        return "redirect:/admin/pendaftaran?success=1";
    }

    // =============================
    // PENDAFTARAN POLI TANPA id_dokter
    // =============================

    @GetMapping("/pendaftaran-poli")
public String pendaftaranPoli(Model model) {
    if (!ensureLogged()) return redirectLogin();

    model.addAttribute("pendaftaranPoliList", pendaftaranPasien.getAll());
    model.addAttribute("pasienList", pasienService.getAllPasien());
    model.addAttribute("pendaftaranBaru", new PendaftaranPasien());

    return "admin/admin-pendaftaran-poli";
}



    @PostMapping("/pendaftaran-poli")
public String prosesPendaftaranPoli(
        @RequestParam("pasienId") Long idPasien,
        @RequestParam("poli") String poli,
        @RequestParam("tglDaftar") String tglDaftar
) {
    if (!ensureLogged()) return redirectLogin();

    Long idAdmin = getLoggedUser().getIdPengguna();

    LocalDate tanggal;
    try { tanggal = LocalDate.parse(tglDaftar); }
    catch (Exception e) { tanggal = LocalDate.now(); }

    // ADMIN MENDAFTARKAN PASIEN
    pendaftaranPasien.daftarManual(idAdmin, idPasien, poli, tanggal, "MENUNGGU");

    return "redirect:/admin/pendaftaran-poli?success=1";
}


    // =============================
    // ANTRIAN
    // =============================

    @GetMapping("/antrian")
    public String listAntrian(Model model) {
        if (!ensureLogged()) return redirectLogin();
        model.addAttribute("pendaftaranPoliList", pendaftaranPasien.getAll());
        return "admin/admin-antrian-list";
    }

    @PostMapping("/pasien/{idDaftar}/status")
    public String updateStatus(@PathVariable Long idDaftar,
                               @RequestParam("statusBaru") String statusBaru) {
        if (!ensureLogged()) return redirectLogin();
        pendaftaranPasien.updateStatus(idDaftar, statusBaru);
        return "redirect:/admin/pendaftaran";
    }

    @PostMapping("/pasien/{idDaftar}/delete")
    public String deletePendaftaran(@PathVariable Long idDaftar) {
        if (!ensureLogged()) return redirectLogin();
        pendaftaranPasien.delete(idDaftar);
        return "redirect:/admin/pendaftaran";
    }

    @GetMapping("/profil")
public String profilAdmin(Model model) {
    if (!ensureLogged()) return redirectLogin();

    // ini object Pengguna yang disimpan di session saat login
    Pengguna pengguna = getLoggedUser();

    // kalau kamu punya entity Admin terpisah dan service-nya:
    var adminDetail = adminService.getByIdPengguna(pengguna.getIdPengguna());
    // kalau belum punya method ini, bikin di AdminService & AdminRepository:
    // Admin findByIdPengguna(Long idPengguna);

    model.addAttribute("pengguna", pengguna);
    model.addAttribute("admin", adminDetail); // boleh null, thymeleaf sudah pakai th:if

    return "admin/admin-profil";
}
@GetMapping("/pendaftaran-poli/edit/{idDaftar}")
public String editPendaftaranPoli(@PathVariable Long idDaftar, Model model) {

    if (!ensureLogged()) return redirectLogin();

    PendaftaranPasien data = pendaftaranPasien.getById(idDaftar);

    model.addAttribute("pendaftaran", data);
    model.addAttribute("pasienList", pasienService.getAllPasien());
    model.addAttribute("pasienRec", data.getPasien()); // FIXED

    return "admin/admin-edit-pendaftaran-poli";
}

@PostMapping("/pendaftaran-poli/update")
public String updatePendaftaranPoli(
        @RequestParam Long idDaftar,
        @RequestParam("pasienId") Long idPasien,
        @RequestParam String poli,
        @RequestParam String tglDaftar
) {
    LocalDate tanggal = LocalDate.parse(tglDaftar);

    pendaftaranPasien.update(idDaftar, idPasien, poli, tanggal);

    return "redirect:/admin/pendaftaran-poli?updated=1";
}

@PostMapping("/pendaftaran-poli/delete/{idDaftar}")
public String deletePendaftaranPoli(@PathVariable Long idDaftar) {
    pendaftaranPasien.delete(idDaftar);
    return "redirect:/admin/pendaftaran-poli?deleted=1";
}

}