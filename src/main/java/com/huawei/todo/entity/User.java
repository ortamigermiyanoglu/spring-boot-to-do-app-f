package com.huawei.todo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sumutella
 * @time 4:52 PM
 * @since 12/15/2019, Sun
 */
@Setter
@Getter
@Table(name = "USERS")
@Entity
public class User extends BaseEntity {
    private String username;
    private String password;
    private Boolean enabled;
    private String email;
    @Column(name = "full_name")
    private String fullName;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ConfirmationToken confirmationToken;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Task> tasks = new ArrayList<>();
}
