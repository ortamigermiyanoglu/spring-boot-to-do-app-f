package com.huawei.todo.mapper.v1;

import com.huawei.todo.dto.v1.UserDto;
import com.huawei.todo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author sumutella
 * @time 5:31 PM
 * @since 12/15/2019, Sun
 */
@Mapper
public interface UserMapper {
    User dtoToEntity(UserDto userDto);
    @Mapping(target = "password", ignore = true)
    UserDto entityToDto(User user);
}
