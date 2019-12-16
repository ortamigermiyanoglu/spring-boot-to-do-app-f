package com.huawei.todo.controller.v1;

import com.huawei.todo.dto.v1.TaskUnitDto;
import com.huawei.todo.dto.v1.UserDto;
import com.huawei.todo.entity.TaskUnit;
import com.huawei.todo.mapper.v1.TaskUnitMapper;
import com.huawei.todo.mapper.v1.UserMapper;
import com.huawei.todo.repository.TaskUnitRepository;
import com.huawei.todo.repository.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }

    // each time, list of to do items is requested, check if the deadline is due
    // if it is, then automatically set status as expired
    @GetMapping("/{taskId}")
    public String showTaskUnits(@PathVariable Integer taskId, Authentication authentication, Model model){

        List<TaskUnit> taskUnitss = new ArrayList<>();
        taskUnitss = taskUnitRepository.findAllByOrderByCreatedDate();
        for (TaskUnit taskUnit :taskUnitss){
            System.out.println(taskUnit.getName());
        }



        UserDto userDto = userMapper.entityToDto(userRepository.findByUsername(authentication.getName()));
        List<TaskUnitDto> taskUnits = taskUnitRepository.findByTaskId(taskId).stream().map(taskUnitMapper::entityToDto).collect(Collectors.toList());

        for(TaskUnitDto taskUnitDto : taskUnits){
            if (taskUnitDto.getDeadline() != null && (taskUnitDto.getDeadline().compareTo(java.sql.Date.valueOf(LocalDate.now())) < 0)){
                String status = taskUnitDto.getStatus().equalsIgnoreCase("Complete")?"Complete" : "Expired";
                taskUnitDto.setStatus(status);
            }
        }
        List<TaskUnit> taskUnitsEntity= taskUnits.stream().map(taskUnitMapper::dtoToEntity).collect(Collectors.toList());
        taskUnitRepository.saveAll(taskUnitsEntity);

        model.addAttribute("loggedUsername", userDto.getFullName());
        model.addAttribute("taskUnits", taskUnits);
        model.addAttribute("taskId", taskId);
        return "task-unit/task-unit-index";
    }


    // new to do item cannot have dependency with other task unit whose
    // deadline is before new to do item creation date
    @GetMapping("/{taskId}/add")
    public String addTaskUnit(@PathVariable Integer taskId, Model model){
        TaskUnitDto taskUnitDto = new TaskUnitDto();
        taskUnitDto.setTaskId(taskId);
        List<TaskUnitDto> candidateParentTaskUnits = taskUnitRepository.findByTaskId(taskId).stream().map(taskUnitMapper::entityToDto).collect(Collectors.toList());

        List<TaskUnitDto> realPossibleParentTaskUnits = new ArrayList<>();

        for (TaskUnitDto taskUnitDto1 : candidateParentTaskUnits){
            if (taskUnitDto1.getDeadline()!=null && (taskUnitDto1.getDeadline().compareTo(java.sql.Date.valueOf(LocalDate.now())) > 0 )){
                realPossibleParentTaskUnits.add(taskUnitDto1);
            }
        }

        model.addAttribute("parentTaskUnits", realPossibleParentTaskUnits);
        model.addAttribute("showOtherOptions", false);
        model.addAttribute("taskUnit", taskUnitDto);
        return "task-unit/task-unit-form";
    }


    @PostMapping("/{taskId}/save")
    public String addTaskUnit(@PathVariable Integer taskId,@ModelAttribute TaskUnitDto taskUnitDto, Model model) throws ParseException {
        taskUnitDto.setCreatedDate(java.sql.Date.valueOf(LocalDate.now()));
        taskUnitRepository.save(taskUnitMapper.dtoToEntity(taskUnitDto));
        return "redirect:/user/tasks/"+taskId;
    }

    // view to do item details
    // if to do item has dependency, then show parent to do items'name
    @GetMapping("/{taskId}/unit/{unitId}/view")
    public String viewUnit(@PathVariable Integer taskId, @PathVariable Integer unitId, Authentication authentication, Model model){
        TaskUnitDto taskUnitDto = taskUnitMapper.entityToDto(taskUnitRepository.findById(unitId).orElse(null));
        UserDto userDto =  userMapper.entityToDto(userRepository.findByUsername(authentication.getName()));
        model.addAttribute("loggedUsername", userDto.getFullName());

        TaskUnitDto parentTaskUnit = new TaskUnitDto();
        if (taskUnitDto.getParentTaskUnitId() != null){
            parentTaskUnit = taskUnitMapper.entityToDto(taskUnitRepository.findById(taskUnitDto.getParentTaskUnitId()).orElse(null));
        }

        if (parentTaskUnit.getName() != null){
            model.addAttribute("parentTasUnitName", parentTaskUnit.getName());
        }

        model.addAttribute("taskUnit", taskUnitDto);
        return "task-unit/task-unit-view";
    }

    @GetMapping("/{taskId}/delete/{unitId}")
    public String deleteTaskUnit(@PathVariable Integer taskId, @PathVariable Integer unitId){
        taskUnitRepository.deleteById(unitId);
        return "redirect:/user/tasks/"+taskId;
    }


    @GetMapping("/{taskId}/unit/{unitId}/update")
    public String updateTaskUnit(@PathVariable Integer taskId, @PathVariable Integer unitId){
        TaskUnitDto  taskUnitDto= taskUnitMapper.entityToDto(taskUnitRepository.findById(unitId).orElse(null));
        taskUnitDto.setStatus("Complete");
        taskUnitRepository.save(taskUnitMapper.dtoToEntity(taskUnitDto));

        return "redirect:/user/tasks/"+taskId;
    }
}
