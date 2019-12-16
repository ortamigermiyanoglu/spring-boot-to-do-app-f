package com.huawei.todo.controller.v1;

import com.huawei.todo.dto.v1.TaskDto;
import com.huawei.todo.dto.v1.UserDto;
import com.huawei.todo.entity.Task;
import com.huawei.todo.entity.User;
import com.huawei.todo.mapper.v1.TaskMapper;
import com.huawei.todo.mapper.v1.UserMapper;
import com.huawei.todo.repository.TaskRepository;
import com.huawei.todo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * @author sumutella
 * @time 8:24 PM
 * @since 12/16/2019, Mon
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TaskControllerTest {

    private TaskController taskController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private TaskMapper taskMapper;

    @Mock
    private Authentication authentication;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        taskController = spy(new TaskController(userRepository, taskRepository));
        doReturn(userMapper).when(taskController).getUserMapper();
        doReturn(taskMapper).when(taskController).getTaskMapper();
    }

    @Test
    void showTasks() {
        User user = findByUsername();
        UserDto userDto = userDto();
        when(userRepository.findByUsername("sumutella")).thenReturn(user);
        when(userMapper.entityToDto(user)).thenReturn(userDto);
        when(authentication.getName()).thenReturn("sumutella");

        String response = taskController.showTasks(model, authentication);

        verify(model).addAttribute("loggedUsername", "Semir Umut Kurt");
        verify(model).addAttribute("tasks", Collections.emptyList());
        assertEquals("task/task-index", response);
    }

    @Test
    void addTask() {
        User user = findByUsername();
        UserDto userDto = userDto();
        Task task = task();
        TaskDto taskDto = taskDto();
        when(userRepository.findByUsername("sumutella")).thenReturn(user);
        when(userMapper.entityToDto(user)).thenReturn(userDto);
        when(taskMapper.dtoToEntity(taskDto)).thenReturn(task);
        when(authentication.getName()).thenReturn("sumutella");

        String response = taskController.addTask(taskDto, authentication);

        assertEquals(1L, (long) taskDto.getUserId());
        verify(taskRepository).save(task);
        assertEquals("redirect:/user/tasks", response);
    }

    @Test
    void deleteTask() {
        String response = taskController.deleteTask(1);

        verify(taskRepository).deleteById(1);
        assertEquals("redirect:/user/tasks", response);
    }

    private User findByUsername() {
        User user = new User();
        user.setId(1);
        user.setUsername("sumutella");
        user.setEmail("sumutella@gmail.com");
        user.setPassword("123");
        user.setFullName("Semir Umut Kurt");
        user.setEnabled(true);
        return user;
    }

    private UserDto userDto() {
        UserDto user = new UserDto();
        user.setId(1);
        user.setUsername("sumutella");
        user.setEmail("sumutella@gmail.com");
        user.setPassword("123");
        user.setFullName("Semir Umut Kurt");
        user.setEnabled(true);
        return user;
    }

    private Task task() {
        Task task = new Task();
        task.setName("Test Task");
        return task;
    }

    private TaskDto taskDto() {
        TaskDto task = new TaskDto();
        task.setName("Test Task");
        return task;
    }
}