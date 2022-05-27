package dev.manool.TODOApp.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@RestController
@Controller
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("projects", projectService.findAll());
        return "projects";
    }

    @GetMapping("/{id}")
    public Project findById(@PathVariable Long id) {
        return projectService.findById(id);
    }

    @PostMapping
    @ResponseBody
    public Project saveProject(
            @Valid
            @RequestBody
            Project newProject) {
        return projectService.saveProject(newProject);
    }

    @PutMapping("/{id}")
    public Project updateProject(
            @PathVariable Long id,
            @Valid
            @RequestBody Project projectInfo
    ) {
        projectInfo.setId(id);
        return projectService.saveProject(projectInfo);
    }

    @DeleteMapping("/{id}")
    @GetMapping("/{id}/delete-project")
    @ResponseBody()
    public ResponseEntity<?> deleteProjectById(@PathVariable Long id) {
        projectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
