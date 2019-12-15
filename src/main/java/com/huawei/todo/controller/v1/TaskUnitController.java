package com.huawei.todo.controller.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sumutella
 * @time 12:36 AM
 * @since 12/16/2019, Mon
 */
@Controller
@RequestMapping("/users/tasks")
public class TaskUnitController {



    @GetMapping("/{taskId}")
    public String showTaskUnits(@PathVariable Integer taskId){
        return null;
    }
}
