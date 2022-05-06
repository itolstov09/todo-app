package dev.manool.TODOApp.tasks;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Data
@NoArgsConstructor
@Entity
@Table()
public class Task {
    @Id
    @GeneratedValue
    long id;

    @NonNull
    @Column(nullable = false)
    String text;

    @NonNull
    @Column(columnDefinition = "TEXT DEFAULT 'NOT_STARTED'")
    @Enumerated(EnumType.STRING)
    Status status;

    @NonNull
    @Column(columnDefinition = "TEXT DEFAULT 'SOMEDAY'")
    @Enumerated(EnumType.STRING)
    Priority priority;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate deadline;

    // сменил FetchType с LAZY на EAGER. Потому как выдавало ошибку. Да костыль. Поэтому тут этот коммент
    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    Set<Subtask> subtasks;

    public Task(
            @NonNull String text,
            @NonNull Status status,
            @NonNull Priority priority) {
        this.text = text;
        this.status = status;
        this.priority = priority;
    }

    public enum Status {
        IN_PROGRESS, NOT_STARTED, JOBISDONE, ON_REVIEW, CANCELED
    }

    public enum Priority {
        SOMEDAY, NORMAL, TODAY, ASAP
    }

}
