package com.huawei.todo.rest;

import com.huawei.todo.dto.v1.TaskUnitDto;
import com.huawei.todo.entity.ResponseEntity;
import com.huawei.todo.entity.TaskUnit;
import com.huawei.todo.mapper.v1.TaskMapper;
import com.huawei.todo.mapper.v1.TaskUnitMapper;
import com.huawei.todo.repository.TaskUnitRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sumutella
 * @time 6:53 PM
 * @since 12/16/2019, Mon
 */
@RestController
@RequestMapping("/api/v1/user/task/taskUnit")
public class TaskUnitRestController {



    private final TaskUnitMapper taskUnitMapper = Mappers.getMapper(TaskUnitMapper.class);

    private final TaskUnitRepository taskUnitRepository;

    public TaskUnitRestController(TaskUnitRepository taskUnitRepository) {
        this.taskUnitRepository = taskUnitRepository;
    }


    @GetMapping("/orderBy/createdDate")
    public ResponseEntity<TaskUnitDto> findAllByOrderByCreatedDate(){
        List<TaskUnitDto> taskUnitDtoList = taskUnitRepository.findAllByOrderByCreatedDate().stream()
                .map(taskUnitMapper::entityToDto).collect(Collectors.toList());

        return new ResponseEntity<TaskUnitDto>("OK", taskUnitDtoList);
    }

    @GetMapping("/orderBy/deadline")
    public ResponseEntity<TaskUnitDto> findAllByOrderByDeadline(){
        List<TaskUnitDto> taskUnitDtoList = taskUnitRepository.findAllByOrderByDeadline().stream()
                .map(taskUnitMapper::entityToDto).collect(Collectors.toList());

        return new ResponseEntity<TaskUnitDto>("OK", taskUnitDtoList);
    }

    @GetMapping("/orderBy/name")
    public ResponseEntity<TaskUnitDto> findAllByOrderByName(){
        List<TaskUnitDto> taskUnitDtoList = taskUnitRepository.findAllByOrderByName().stream()
                .map(taskUnitMapper::entityToDto).collect(Collectors.toList());

        return new ResponseEntity<TaskUnitDto>("OK", taskUnitDtoList);
    }

    @GetMapping("/orderBy/status")
    public ResponseEntity<TaskUnitDto> findAllByOrderByStatus(){
        List<TaskUnitDto> taskUnitDtoList = taskUnitRepository.findAllByOrderByStatus().stream()
                .map(taskUnitMapper::entityToDto).collect(Collectors.toList());

        return new ResponseEntity<TaskUnitDto>("OK", taskUnitDtoList);
    }

    @GetMapping("/filterBy/complete")
    public ResponseEntity<TaskUnitDto> filterComplete(@PathVariable String filter){
        List<TaskUnitDto> taskUnitDtoList = taskUnitRepository.findByStatusIgnoreCase("Complete").stream()
                .map(taskUnitMapper::entityToDto).collect(Collectors.toList());

        return new ResponseEntity<TaskUnitDto>("OK", taskUnitDtoList);
    }

    @GetMapping("/filterBy/notComplete")
    public ResponseEntity<TaskUnitDto> filterNotComplete(@PathVariable String filter){
        List<TaskUnitDto> taskUnitDtoList = taskUnitRepository.findByStatusIgnoreCase("Not Complete").stream()
                .map(taskUnitMapper::entityToDto).collect(Collectors.toList());

        return new ResponseEntity<TaskUnitDto>("OK", taskUnitDtoList);
    }

    @GetMapping("/filterBy/expired")
    public ResponseEntity<TaskUnitDto> filterExpired(@PathVariable String filter){
        List<TaskUnitDto> taskUnitDtoList = taskUnitRepository.findByStatusIgnoreCase("Expired").stream()
                .map(taskUnitMapper::entityToDto).collect(Collectors.toList());

        return new ResponseEntity<TaskUnitDto>("OK", taskUnitDtoList);
    }
}
