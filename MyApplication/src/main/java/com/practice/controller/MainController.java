package com.practice.controller;

import com.practice.model.entity.UserAccount;
import com.practice.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    private final UserRepository repository;
    private final PasswordEncoder encoder;


    @Autowired
    public MainController(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("user", new UserAccount());
        return "newUser";
    }

    @PostMapping("/signup")
    public String register(@ModelAttribute("user") UserAccount user){
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repository.save(user);
        return "home";
    }
}
