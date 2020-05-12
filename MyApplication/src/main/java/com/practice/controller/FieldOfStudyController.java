package com.practice.controller;

import com.practice.model.entity.Academy;
import com.practice.model.entity.FieldOfStudy;
import com.practice.model.repository.AcademyRepository;
import com.practice.model.repository.FieldOfStudyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NamedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class FieldOfStudyController {
    private final FieldOfStudyRepository fieldOfStudyRepository;
    private final AcademyRepository academyRepository;

    public FieldOfStudyController(FieldOfStudyRepository fieldOfStudyRepository,  AcademyRepository academyRepository) {
        this.fieldOfStudyRepository = fieldOfStudyRepository;
        this.academyRepository = academyRepository;
    }

    @GetMapping("/fieldsOfStudy")
    public String getFieldsOfStudy(Model model){
        List <FieldOfStudy> fieldsOfStudy = fieldOfStudyRepository.findAll();
        model.addAttribute("fieldsOfStudy", fieldsOfStudy);
        return "/fieldsOfStudy";
    }

    @GetMapping("/fieldsOfStudy/search")
    public String searchFieldOfStudy(Model model, @RequestParam("searchField") String searchField){
        if(searchField.equals(""))
        {
            getFieldsOfStudy(model);
        } else {
            String searchField2 = searchField.substring(0, 1).toUpperCase() + searchField.substring(1).toLowerCase();
            List<FieldOfStudy> fieldsOfStudy = fieldOfStudyRepository.findFieldOfStudyByNameContains(searchField);
            fieldsOfStudy.addAll(fieldOfStudyRepository.findFieldOfStudiesByAcademy_NameContains(searchField));
            if (!searchField2.equals(searchField)) {
                fieldsOfStudy.addAll(fieldOfStudyRepository.findFieldOfStudyByNameContains(searchField2));
                fieldsOfStudy.addAll(fieldOfStudyRepository.findFieldOfStudiesByAcademy_NameContains(searchField2));
            }
            model.addAttribute("fieldsOfStudy", fieldsOfStudy);
        }
        return "/fieldsOfStudy";
    }

    @GetMapping("/fieldsOfStudy/{id}")
    public String getFieldsOfStudyForAcademy(Model model, @PathVariable("id") Long id){
        Academy academy = academyRepository.getOne(id);
        List<FieldOfStudy> fieldsOfStudy = fieldOfStudyRepository.findFieldOfStudiesByAcademy(academy);
        model.addAttribute("academy", academy);
        model.addAttribute("fieldsOfStudy", fieldsOfStudy);
        return "/fieldsOfStudy";
    }

    @GetMapping("/fieldsOfStudy/delete/{id}")
    public String deleteFieldOfStudy(@PathVariable("id") Long id){
        fieldOfStudyRepository.deleteById(id);
        return "redirect:/fieldsOfStudy";
    }

    @PostMapping("/fieldsOfStudy/update/{id}")
    public String updateFieldOfStudy(@PathVariable("id") Long id, @RequestParam("name") String name){
        FieldOfStudy fieldOfStudy = fieldOfStudyRepository.getOne(id);
        fieldOfStudy.setName(name);
        fieldOfStudyRepository.save(fieldOfStudy);
        return "redirect:/fieldsOfStudy";
    }

    @GetMapping("/addFieldOfStudy")
    public String addFieldOfStudy(Model model){
        List<Academy> academies = academyRepository.findAll();
        model.addAttribute("academies", academies);
        model.addAttribute("fieldOfStudy", new FieldOfStudy());
        return "addFieldOfStudy";
    }

    @PostMapping("/addFieldOfStudy")
    public String addNewFieldOfStudy(@ModelAttribute("fieldOfStudy") FieldOfStudy fieldOfStudy){
        fieldOfStudyRepository.save(fieldOfStudy);
        return "redirect:/fieldsOfStudy";
    }
}

