package com.practice.controller;

import com.practice.model.entity.Academy;
import com.practice.model.entity.Course;
import com.practice.model.entity.FieldOfStudy;
import com.practice.model.repository.AcademyRepository;
import com.practice.model.repository.CourseRepository;
import com.practice.model.repository.FieldOfStudyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CourseController {
    private final CourseRepository courseRepository;
    private final FieldOfStudyRepository fieldOfStudyRepository;
    private final AcademyRepository academyRepository;

    public CourseController(CourseRepository courseRepository,
                            FieldOfStudyRepository fieldOfStudyRepository, AcademyRepository academyRepository) {
        this.courseRepository = courseRepository;
        this.fieldOfStudyRepository = fieldOfStudyRepository;
        this.academyRepository = academyRepository;
    }

    @GetMapping("/courses")
    public String getCourses(Model model){
        List <Course> courses = courseRepository.findAll();
        List<Academy> academies = academyRepository.findAll();
        model.addAttribute("courses", courses);
        model.addAttribute("academies", academies);
        return "/courses";
    }

    @GetMapping("/courses/search")
    public String searchCourses(@RequestParam("searchField") String searchField, Model model){
        if(searchField.equals(""))
        {
            getCourses(model);
        } else {
            String searchField2 = searchField.substring(0, 1).toUpperCase() + searchField.substring(1).toLowerCase();
            List<Course> courses = courseRepository.findCourseByNameContains(searchField);
            if (!searchField2.equals(searchField)) {
                courses.addAll(courseRepository.findCourseByNameContains(searchField2));
            }
            model.addAttribute("courses", courses);
        }
        return "/courses";
    }

    @GetMapping("/courses/{id}")
    public String getFieldsOfStudyForAcademy(Model model, @PathVariable("id") Long id){
        FieldOfStudy fieldOfStudy = fieldOfStudyRepository.getOne(id);
        List<Course> courses = courseRepository.findCoursesByFieldsOfStudy(fieldOfStudy);
        model.addAttribute("fieldOfStudy", fieldOfStudy);
        model.addAttribute("courses", courses);
        return "/courses";
    }

    @GetMapping("/courses/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long id){
        Course course = courseRepository.getOne(id);
        for (FieldOfStudy fieldOfStudy: course.getFieldsOfStudy()
             ) {
                fieldOfStudy.removeCourse(course);
            System.out.println(fieldOfStudy.getCourses());
        }
        courseRepository.delete(course);
        return "redirect:/courses";
    }

    @PostMapping("/courses/update/{id}")
    public String updateCourse(@PathVariable("id") Long id, @RequestParam("name") String name){
        Course course = courseRepository.getOne(id);
        course.setName(name);
        courseRepository.save(course);
        return "redirect:/courses";
    }


    @GetMapping("/addCourse")
    public String addCourse(Model model, @RequestParam("academy") Academy academy){
        List<FieldOfStudy> fieldsOfStudy = fieldOfStudyRepository.findFieldOfStudiesByAcademy(academy);
        model.addAttribute("fieldsOfStudy", fieldsOfStudy);
        model.addAttribute("course", new Course());
        return "addCourse";
    }

    @PostMapping("/addCourse")
    public String addNewCourse(@ModelAttribute("course") Course course){
        for (FieldOfStudy fieldOfStudy:course.getFieldsOfStudy()) {
            fieldOfStudy.addCourse(course);
        }
        courseRepository.save(course);

        return "redirect:/courses";
    }
}
