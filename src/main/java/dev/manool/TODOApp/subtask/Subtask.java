package dev.manool.TODOApp.subtask;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.manool.TODOApp.task.Task;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sub_tasks")
public class Subtask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NonNull
    @Column(nullable = false)
    String text;

    @Column(name = "is_done", columnDefinition = "BOOLEAN DEFAULT false")
    boolean isDone;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    @JsonIgnore
    Task task;



    public Subtask(@NonNull String text, boolean isDone, Task task) {
        this.text = text;
        this.isDone = isDone;
        this.task = task;
    }

    public Subtask() {
    }
}
