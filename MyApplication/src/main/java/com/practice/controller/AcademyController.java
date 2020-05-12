package com.practice.controller;

import com.practice.model.entity.Academy;
import com.practice.model.repository.AcademyRepository;
import com.practice.model.repository.FieldOfStudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AcademyController {
    private final AcademyRepository academyRepository;
    private final FieldOfStudyRepository fieldOfStudyRepository;

    @Autowired
    public AcademyController(AcademyRepository academyRepository, FieldOfStudyRepository fieldOfStudyRepository) {
        this.academyRepository = academyRepository;
        this.fieldOfStudyRepository = fieldOfStudyRepository;
    }

    @GetMapping("/academies")
    public String getAcademies(Model model) {
        List<Academy> academies = academyRepository.findAll();
        model.addAttribute("academies", academies);
        return "/academies";
    }

    @GetMapping("/academies/search")
    public String searchAcademies(Model model, @RequestParam("searchField") String searchField) {
        String searchField2 = searchField.substring(0, 1).toUpperCase() + searchField.substring(1).toLowerCase();
        List<Academy> academies = academyRepository.findAcademiesByNameContains(searchField);
        if(!searchField2.equals(searchField)){
            academies.addAll(academyRepository.findAcademiesByNameContains(searchField2));
        }
        model.addAttribute("academies", academies);
        return "/academies";
    }


    @GetMapping("/academies/delete/{id}")
    public String deleteAcademy(@PathVariable("id") Long id) {
        Academy academy = academyRepository.getOne(id);
        fieldOfStudyRepository.deleteAll(academy.getFieldsOfStudy());
        academyRepository.deleteById(id);
        return "redirect:/academies";
    }

    @PostMapping("/academies/update/{id}")
    public String updateAcademy(@RequestParam("name") String name, @PathVariable("id") long id) {
        Academy academy = academyRepository.getOne(id);
        academy.setName(name);
        academyRepository.save(academy);
        return "redirect:/academies";
    }


    @GetMapping("/addAcademy")
    public String addAcademy(Model model) {
        model.addAttribute("academy", new Academy());
        return "addAcademy";
    }

    @PostMapping("/addAcademy")
    public String addNewAcademy(@ModelAttribute("academy") Academy academy) {
        if (!academyRepository.existsByName(academy.getName())) {
            academyRepository.save(academy);
            return "redirect:/academies";
        } else {
            return "/addAcademyError";
        }
    }
}
