package com.huawei.todo.mapper.v1;

import com.huawei.todo.dto.v1.AuthorityDto;
import com.huawei.todo.entity.Authority;
import org.mapstruct.Mapper;

/**
 * @author sumutella
 * @time 5:35 PM
 * @since 12/15/2019, Sun
 */
@Mapper
public interface AuthorityMapper {
    Authority dtoToEntity(AuthorityMapper authorityMapper);
    AuthorityDto entityToDto(Authority authority);
}
