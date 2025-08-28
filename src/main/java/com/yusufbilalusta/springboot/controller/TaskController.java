package com.yusufbilalusta.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yusufbilalusta.springboot.model.Task;
import com.yusufbilalusta.springboot.service.ProjectService;
import com.yusufbilalusta.springboot.service.TaskService;


@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private ProjectService projectService;

    
    @GetMapping("list/{project_id}")
    public String listTasksOfProject(@PathVariable("project_id") Long projectId, Model model) {
        model.addAttribute("tasks", taskService.getTasksByProjectId(projectId));
        return "taskList";
    }

    @GetMapping("/new/{project_id}")
    public String showCreateTaskForm(Model model, @PathVariable("project_id") Long projectId) {
        model.addAttribute("task", new Task());
        model.addAttribute("selectedProject", projectService.getProjectById(projectId).orElse(null));
        model.addAttribute("selectedProjectId", projectId); // Also add projectId since HTML expects it
        model.addAttribute("projectId", projectId); //For delete button
        return "createTask";
    }

    @PostMapping
    public String createTask(@ModelAttribute Task task, @RequestParam Long projectId) {
        // Task'i proje ile ilişkilendir
        task.setProject(projectService.getProjectById(projectId).orElse(null));
        taskService.saveTask(task);
        return "redirect:/projects/" + projectId; // Proje detay sayfasına yönlendir
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/projects";
    }

}
