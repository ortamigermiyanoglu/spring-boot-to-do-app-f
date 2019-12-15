package com.huawei.todo.dto.v1;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sumutella
 * @time 5:22 PM
 * @since 12/15/2019, Sun
 */
@Setter
@Getter
public class TaskDto extends BaseEntityDto{
    private String name;
    private Integer userId;


    private List<TaskUnitDto> taskUnits = new ArrayList<>();
}
