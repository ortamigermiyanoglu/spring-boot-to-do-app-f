package com.huawei.todo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author sumutella
 * @time 4:59 PM
 * @since 12/15/2019, Sun
 */
@Setter
@Getter
@Table(name = "TASK_UNITS")
@Entity
public class TaskUnit extends BaseEntity {
    private String name;
    private String description;
    @Column(name = "created_date")
    private Date createdDate;
    private Date deadline;
    private String status;
    @Column(name = "task_id")
    private Integer taskId;
    @Column(name = "pre_task_unit_id")
    private Integer parentTaskUnitId;


    @ManyToOne
    @JoinColumn(name = "pre_task_unit_id", insertable = false, updatable = false)
    private TaskUnit parentTaskUnit;

    @OneToMany(mappedBy = "parentTaskUnit", cascade = CascadeType.ALL)
    private List<TaskUnit> childrenTaskUnits = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", insertable = false, updatable = false)
    private Task task;
}
