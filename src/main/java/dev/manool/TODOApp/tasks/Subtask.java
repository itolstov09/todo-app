package dev.manool.TODOApp.tasks;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

//TODO Перевести на lombok
@Entity
@Table(name = "sub_tasks")
public class Subtask {
    @Id
    @GeneratedValue
    long id;

    @Column(nullable = false)
    String text;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    boolean isDone;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    @JsonIgnore
    Task task;


    public Subtask() {
    }

    public Subtask(String text, boolean isDone, Task task) {
        this.text = text;
        this.isDone = isDone;
        this.task = task;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", isDone=" + isDone +
                ", taskId=" + task.getId() +
                '}';
    }

}
