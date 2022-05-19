package dev.manool.TODOApp.subtask;

import dev.manool.TODOApp.task.Task;
import dev.manool.TODOApp.task.TaskService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
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
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    public void givenSubtasks_whenGetSubtasks_thenReturnJSONArray() throws Exception {
        given(subtaskService.findAllSubtasks()).willReturn(subtasks);
        mockMvc .perform(get("/subtasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].text", is(subtasks.get(0).getText())))
                .andExpect(jsonPath("$[1].text", is(subtasks.get(1).getText())));
    }

}