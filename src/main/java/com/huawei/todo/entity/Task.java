package com.huawei.todo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sumutella
 * @time 4:58 PM
 * @since 12/15/2019, Sun
 */
@Setter
@Getter
@Table(name = "TASKS")
@Entity
public class Task extends BaseEntity {
    private String name;
    @Column(name = "user_id")
    private Integer userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "task")
    private List<TaskUnit> taskUnits = new ArrayList<>();
}
