package com.yusufbilalusta.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yusufbilalusta.springboot.model.Project;
import com.yusufbilalusta.springboot.service.ProjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService; 
    
    @GetMapping
    public String listProjects(Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        return "list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("project", new Project());
        return "form";
    }

    @PostMapping
    public String saveProject(@ModelAttribute Project project) {
        projectService.saveProject(project);
        return "redirect:/projects";
    }

    @GetMapping("/{id}")
    public String showProject(@PathVariable Long id, Model model) {
        model.addAttribute("project", projectService.getProjectById(id).orElse(null));
        return "detail";
    }

}
