package dev.manool.TODOApp.project;

import dev.manool.TODOApp.task.Task;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @NonNull
    @NotBlank(message = "Project name is required!")
    @Column(nullable = false)
    String name;

    String description;

    @Column(name = "is_done")
    boolean isDone;

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    Set<Task> tasks = new HashSet<>();

    public Project(@NonNull String name) {
        this.name = name;
    }
}
