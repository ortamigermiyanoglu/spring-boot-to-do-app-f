package com.huawei.todo.mapper.v1;

import com.huawei.todo.dto.v1.ConfirmationTokenDto;
import com.huawei.todo.entity.ConfirmationToken;
import org.mapstruct.Mapper;

/**
 * @author sumutella
 * @time 5:34 PM
 * @since 12/15/2019, Sun
 */
@Mapper
public interface ConfirmationTokenMapper {
    ConfirmationToken dtoToEntity(ConfirmationTokenDto confirmationTokenDto);
    ConfirmationTokenDto entityToDto(ConfirmationToken confirmationToken);

}
