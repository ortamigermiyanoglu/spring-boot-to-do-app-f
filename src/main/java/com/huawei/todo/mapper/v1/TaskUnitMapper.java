package com.huawei.todo.mapper.v1;

import com.huawei.todo.dto.v1.TaskDto;
import com.huawei.todo.dto.v1.TaskUnitDto;
import com.huawei.todo.entity.Task;
import com.huawei.todo.entity.TaskUnit;
import org.mapstruct.Mapper;

/**
 * @author sumutella
 * @time 1:23 AM
 * @since 12/16/2019, Mon
 */
@Mapper
public interface TaskUnitMapper {
    TaskUnit dtoToEntity(TaskUnitDto taskUnitDto);
    TaskUnitDto entityToDto(TaskUnit taskUnit);
}
