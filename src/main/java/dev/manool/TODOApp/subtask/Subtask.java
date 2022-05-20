package dev.manool.TODOApp.subtask;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import dev.manool.TODOApp.task.Task;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "sub_tasks")
public class Subtask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NonNull
    @NotBlank(message = "Subtask text is required!")
    @Column(nullable = false)
    String text;

    @Column(name = "is_done", columnDefinition = "BOOLEAN DEFAULT false")
    boolean isDone;

    @NotNull(message = "Subtask task relation is required!")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    @EqualsAndHashCode.Exclude @ToString.Exclude
    // Для исправления рекурсивной сериализации
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIncludeProperties({"id"})
    Task task;

    public Subtask(@NonNull String text, boolean isDone, @NonNull Task task) {
        this.text = text;
        this.isDone = isDone;
        this.task = task;
    }

    public Subtask(@NonNull String text, @NonNull Task task) {
        this.text = text;
        this.task = task;
    }

    public Subtask() {
    }
}
