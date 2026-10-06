package com.example.demospringboot.controller;

import com.example.demospringboot.entity.Pasien;
import com.example.demospringboot.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class RegisterController {

    @Autowired
    private PasienService pasienService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("pasienInfo", new Pasien());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("pasienInfo") Pasien pasien) {

        pasien.setRole("pasien");
        pasienService.registerPasien(pasien);

        return "redirect:/login";
    }
}