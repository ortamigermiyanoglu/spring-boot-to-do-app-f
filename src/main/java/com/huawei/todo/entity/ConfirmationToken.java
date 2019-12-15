package com.huawei.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * @author sumutella
 * @time 4:57 PM
 * @since 12/15/2019, Sun
 */
@Entity
@Setter
@Getter
@Table(name = "CONFIRMATION_TOKEN")
public class ConfirmationToken extends BaseEntity {
    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "user_id")
    private Integer userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;


}
