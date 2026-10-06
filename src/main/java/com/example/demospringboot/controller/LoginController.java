package com.example.demospringboot.controller;

import com.example.demospringboot.entity.Pengguna;
import com.example.demospringboot.repository.PenggunaRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private PenggunaRepository penggunaRepository;

    @GetMapping("/")
    public String root() {
        return "redirect:/login";
    }
    
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/validateLogin")
    public String validateLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model,
            HttpSession session
    ) {
        Optional<Pengguna> opt = penggunaRepository.findByUsername(username);

        if (opt.isEmpty()) {
            model.addAttribute("error", "Username atau password salah");
            return "login";
        }

        Pengguna user = opt.get();

        if (!password.equals(user.getPassword())) {
            model.addAttribute("error", "Username atau password salah");
            return "login";
        }

        // clear semua session dulu
        session.removeAttribute("logAdmin");
        session.removeAttribute("logDokter");
        session.removeAttribute("logPerawat");
        session.removeAttribute("logPasien");

        String role = user.getRole();

        switch (role) {
            case "admin" -> {
                session.setAttribute("logAdmin", user);
                return "redirect:/admin/admin-menu";
            }
            case "dokter" -> {
                session.setAttribute("logDokter", user);
                return "redirect:/dokter/menu";
            }
            case "perawat" -> {
                session.setAttribute("logPerawat", user);
                return "redirect:/perawat/dashboardPerawat";
            }
            case "pasien" -> {
                session.setAttribute("logPasien", user);
                return "redirect:/pasien/dashboardPasien";
            }
            default -> {
                model.addAttribute("error", "Role tidak dikenali");
                return "login";
            }
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
