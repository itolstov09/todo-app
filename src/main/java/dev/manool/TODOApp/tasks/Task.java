package dev.manool.TODOApp.tasks;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

//TODO  Перевести на lobmok
//@Data
//@RequiredArgsConstructor
//// TODO разобраться как работает
//@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue
    // TODO разобраться и вернуть final
    private long id;

    @Column(nullable = false)
    private String text;

    // TODO валидация по тексту статуса и приоритета
//    @Column(columnDefinition = "TEXT DEFAULT Not Started")
    Status status;
//    @Column(columnDefinition = "TEXT DEFAULT Normal")
    Priority priority;

    LocalDate deadline;

    // сменил FetchType с LAZY на EAGER. Потому как выдавало ошибку. Да костыль. Поэтому тут этот коммент
    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    Set<Subtask> subtasks;

    public Task() {
    }

    public Task(String text, Status status, Priority priority, LocalDate deadline) {
        this.text = text;
        this.status = status;
        this.priority = priority;
        this.deadline = deadline;
    }

    public enum Status {
        IN_PROGRESS, NOT_STARTED, JOBISDONE, ON_REVIEW, CANCELED
    }

    public enum Priority {
        SOMEDAY, NORMAL, TODAY, ASAP
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Set<Subtask> getSubTasks() {
        return subtasks;
    }

    public void setSubTasks(Set<Subtask> subtasks) {
        this.subtasks = subtasks;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", status=" + status +
                ", priority=" + priority +
                ", deadline=" + deadline +
                ", subTasks=" + subtasks +
                '}';
    }
}
