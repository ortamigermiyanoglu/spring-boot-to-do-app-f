package com.huawei.todo.dto.v1;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sumutella
 * @time 5:21 PM
 * @since 12/15/2019, Sun
 */
@Setter
@Getter
public class UserDto extends BaseEntityDto {
    private String username;
    private String password;
    private String fullName;

    private List<TaskDto> tasks = new ArrayList<>();
}
