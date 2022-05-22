package dev.manool.TODOApp.subtask;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.manool.TODOApp.subtask.exceptions.SubtaskNotFoundException;
import dev.manool.TODOApp.task.Task;
import dev.manool.TODOApp.task.TaskService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(value = SpringRunner.class)
@WebMvcTest(controllers = SubtaskController.class)
class SubtaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubtaskService subtaskService;

    @MockBean
    private TaskService taskService;

    private Task task;
    private List<Subtask> subtasks;

    @BeforeEach
    public void runBeforeEachTest() {
        task = new Task("Test task 1");

        Subtask subtask1 = new Subtask("Subtask 1", task);
        Subtask subtask2 = new Subtask("Subtask 2", task);

        subtasks = Arrays.asList(subtask1, subtask2);

    }


    @AfterEach
    public void runAfterEachTest() {
        task = null;
        subtasks = null;
    }


    @Test
    public void givenSubtasks_whenGetSubtasks_thenStatusOK() throws Exception {
        given(subtaskService.findAllSubtasks()).willReturn(subtasks);
        mockMvc.perform(get("/subtasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenSubtasks_whenGetSubtasksWithTaskId_thenStatusOK() throws Exception {
        given(subtaskService.findSubtasksByTaskId(Mockito.anyLong())).willReturn(subtasks);
        mockMvc.perform(get("/tasks/1/subtasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenSubtask_whenGetSubtaskByid_thenStatusOK() throws Exception {
        given(subtaskService.findSubtaskById(Mockito.anyLong())).willReturn(subtasks.get(0));
        mockMvc.perform(get("/subtasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenInvalidId_whenGetSubtaskById_thenStatusNotFound() throws Exception {
        when(subtaskService.findSubtaskById(Mockito.anyLong())).thenThrow(SubtaskNotFoundException.class);
        mockMvc.perform(get("/subtasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenValidBody_whenPostSubtask_thenStatusCreated() throws Exception {
        Subtask validSubtask = new Subtask("Valid subtask", task);
        when(subtaskService.save(any(Subtask.class), Mockito.anyLong())).thenReturn(validSubtask);

        mockMvc.perform(
                        post("/tasks/1/subtasks/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(subtaskToJSON(validSubtask)))
                .andExpect(status().isCreated());
    }

    @Test
    public void givenValidBody_whenPutSubtask_thenStatusOK() throws Exception {
        Subtask validSubtask = new Subtask("Valid subtask", task);

        when(subtaskService.save(any(Subtask.class), Mockito.anyLong(), Mockito.anyLong())).thenReturn(validSubtask);

        mockMvc.perform(
                        put("/tasks/1/subtasks/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(subtaskToJSON(validSubtask)))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    private static String subtaskToJSON(Subtask subtask) {
        return new ObjectMapper().writeValueAsString(subtask);
    }

}