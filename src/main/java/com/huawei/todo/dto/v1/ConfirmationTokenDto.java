package com.huawei.todo.dto.v1;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @author sumutella
 * @time 5:24 PM
 * @since 12/15/2019, Sun
 */
@Setter
@Getter
public class ConfirmationTokenDto extends BaseEntityDto {
    private String confirmationToken;
    private Date createdDate;
    private Integer userId;
}
