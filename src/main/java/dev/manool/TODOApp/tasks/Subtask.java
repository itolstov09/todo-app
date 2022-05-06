package dev.manool.TODOApp.tasks;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;

//TODO Перевести на lombok
@Data
@Entity
@Table(name = "sub_tasks")
public class Subtask {
    @Id
    @GeneratedValue
    long id;

    @NonNull
    @Column(nullable = false)
    String text;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    boolean isDone;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    @JsonIgnore
    Task task;



    public Subtask(String text, boolean isDone, Task task) {
        this.text = text;
        this.isDone = isDone;
        this.task = task;
    }

    public Subtask() {
    }
}
