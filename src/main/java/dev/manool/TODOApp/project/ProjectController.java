package dev.manool.TODOApp.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> findAll() {
        return projectService.findAll();
    }

    @GetMapping("/{id}")
    public Project findById(@PathVariable Long id) {
        return projectService.findById(id);
    }

    @PostMapping
    public Project saveProject(@RequestBody Project newProject) {
        return projectService.saveProject(newProject);
    }

    @PutMapping("/{id}")
    public Project updateProject(
            @PathVariable Long id,
            @RequestBody Project projectInfo
    ) {
        projectInfo.setId(id);
        return projectService.saveProject(projectInfo);
    }
}
