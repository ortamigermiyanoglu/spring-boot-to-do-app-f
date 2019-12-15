package com.huawei.todo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author sumutella
 * @time 4:55 PM
 * @since 12/15/2019, Sun
 */
@Setter
@Getter
@Table(name = "AUTHORITIES")
@Entity
public class Authority {
    @Id
    private String username;
    private String authority;

    @OneToOne()
    @JoinColumn(name = "username", insertable = false, updatable = false)
    private User user;
}
