package com.huawei.todo.controller.v1;

import com.huawei.todo.dto.v1.TaskDto;
import com.huawei.todo.dto.v1.UserDto;
import com.huawei.todo.mapper.v1.TaskMapper;
import com.huawei.todo.mapper.v1.UserMapper;
import com.huawei.todo.repository.TaskRepository;
import com.huawei.todo.repository.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * @author sumutella
 * @time 9:17 PM
 * @since 12/15/2019, Sun
 */
@Controller
@RequestMapping("/user")
public class TaskController {
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private final TaskMapper taskMapper = Mappers.getMapper(TaskMapper.class);

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public TaskController(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks")
    public String showTasks(Model model, Authentication authentication){
        UserDto userDto = userMapper.entityToDto(userRepository.findByUsername(authentication.getName()));
        model.addAttribute("loggedUsername", userDto.getFullName());
        model.addAttribute("tasks",taskRepository.findByUser_Id(userDto.getId()).stream().map(taskMapper::entityToDto).collect(Collectors.toList()));
        return "task/task-index";
    }

    @GetMapping("/tasks/add")
    public String addTask(Model model, Authentication authentication){
        UserDto userDto = userMapper.entityToDto(userRepository.findByUsername(authentication.getName()));
        model.addAttribute("loggedUsername", userDto.getFullName());

        model.addAttribute("user", userDto);
        model.addAttribute("task", new TaskDto());
        return "task/task-form";
    }

    @PostMapping("/tasks/save")
    public String addTask(@ModelAttribute("task") TaskDto taskDto, Authentication authentication){
        UserDto userDto = userMapper.entityToDto(userRepository.findByUsername(authentication.getName()));
        taskDto.setUserId(userDto.getId());

        taskRepository.save(taskMapper.dtoToEntity(taskDto));

        return "redirect:/user/tasks";
    }

    @GetMapping("/tasks/{taskId}/delete")
    public String deleteTask(@PathVariable Integer taskId){
        taskRepository.deleteById(taskId);
        return "redirect:/user/tasks";
    }

}
