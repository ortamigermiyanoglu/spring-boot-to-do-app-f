package com.huawei.todo.mapper.v1;

import com.huawei.todo.dto.v1.TaskDto;
import com.huawei.todo.entity.Task;
import org.mapstruct.Mapper;

/**
 * @author sumutella
 * @time 5:32 PM
 * @since 12/15/2019, Sun
 */
@Mapper
public interface TaskMapper {
    Task dtoToEntity(TaskDto taskDto);
    TaskDto entityToDto(Task task);
}
