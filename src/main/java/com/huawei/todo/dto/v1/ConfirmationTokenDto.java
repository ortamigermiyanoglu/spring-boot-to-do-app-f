package com.huawei.todo.dto.v1;

import com.huawei.todo.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

/**
 * @author sumutella
 * @time 5:24 PM
 * @since 12/15/2019, Sun
 */
@Setter
@Getter
@NoArgsConstructor
public class ConfirmationTokenDto{
    private Integer id;
    private String confirmationToken;
    private Date createdDate;
    private Integer userId;

    public ConfirmationTokenDto(UserDto user) {
        this.userId = user.getId();
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }


}
