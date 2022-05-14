package dev.manool.TODOApp.project;

import dev.manool.TODOApp.task.Task;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
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
    @Column(nullable = false)
    String name;

    String description;

    @Column(name = "is_done")
    boolean isDone;

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    Set<Task> tasks;

    public Project(@NonNull String name) {
        this.name = name;
    }
}
