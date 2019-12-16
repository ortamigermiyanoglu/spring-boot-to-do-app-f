package com.huawei.todo.controller.v1;

import com.huawei.todo.dto.v1.TaskUnitDto;
import com.huawei.todo.dto.v1.UserDto;
import com.huawei.todo.entity.TaskUnit;
import com.huawei.todo.mapper.v1.TaskUnitMapper;
import com.huawei.todo.mapper.v1.UserMapper;
import com.huawei.todo.repository.TaskUnitRepository;
import com.huawei.todo.repository.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sumutella
 * @time 12:36 AM
 * @since 12/16/2019, Mon
 */
@Controller
@RequestMapping("/user/tasks")
public class TaskUnitController {


    private final TaskUnitMapper taskUnitMapper = Mappers.getMapper(TaskUnitMapper.class);
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    private final TaskUnitRepository taskUnitRepository;
    private final UserRepository userRepository;

    public TaskUnitController(TaskUnitRepository taskUnitRepository, UserRepository userRepository) {
        this.taskUnitRepository = taskUnitRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("/{taskId}")
    public String showTaskUnits(@PathVariable Integer taskId, Authentication authentication, Model model){
        UserDto userDto = userMapper.entityToDto(userRepository.findByUsername(authentication.getName()));
        List<TaskUnitDto> taskUnits = taskUnitRepository.findByTaskId(taskId).stream().map(taskUnitMapper::entityToDto).collect(Collectors.toList());

        for(TaskUnitDto taskUnitDto : taskUnits){
            if (taskUnitDto != null){
                System.out.println(taskUnitDto.getName());
            }
        }

        model.addAttribute("loggedUsername", userDto.getFullName());
        model.addAttribute("taskUnits", taskUnits);
        model.addAttribute("taskId", taskId);
        return "task-unit/task-unit-index";
    }
}
