package dev.manool.TODOApp.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.manool.TODOApp.project.Project;
import dev.manool.TODOApp.subtask.Subtask;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;


@Data
@NoArgsConstructor
@Entity
@Table()
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NonNull
    @NotBlank(message = "Task text is required!")
    @Column(nullable = false)
    String text;

    @NonNull
    @Enumerated(EnumType.STRING)
    Status status = Status.NOT_STARTED; //Указываем значение по умолчанию

    @NonNull
    @Enumerated(EnumType.STRING)
    Priority priority = Priority.SOMEDAY;

    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate deadline;

    @UpdateTimestamp
    @Column(name = "update_date_time")
    LocalDateTime updateDateTime;

    // сменил FetchType с LAZY на EAGER. Потому как выдавало ошибку. Да костыль. Поэтому тут этот коммент
    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    Set<Subtask> subtasks;


    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @JsonIgnore
    Project project;


    public Task(
            @NonNull String text,
            @NonNull Status status,
            @NonNull Priority priority,
            @NonNull Project project) {
        this.text = text;
        this.status = status;
        this.priority = priority;
        this.project = project;
    }

    public enum Status {
        IN_PROGRESS, NOT_STARTED, JOBISDONE, ON_REVIEW, CANCELED
    }

    public enum Priority {
        SOMEDAY, NORMAL, TODAY, ASAP
    }

}
