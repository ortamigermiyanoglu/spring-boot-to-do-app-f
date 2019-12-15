package com.huawei.todo.mapper.v1;

import com.huawei.todo.dto.v1.BaseEntityDto;
import com.huawei.todo.entity.BaseEntity;
import org.mapstruct.Mapper;

/**
 * @author sumutella
 * @time 5:30 PM
 * @since 12/15/2019, Sun
 */
@Mapper
public interface BaseEntityMapper {
    BaseEntity dtoToEntity(BaseEntityDto baseEntityDto);
    BaseEntityDto entityToDto(BaseEntity baseEntity);
}
