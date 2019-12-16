package com.huawei.todo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Date createdDate;
    private Date deadline;
    private String status;
    @Column(name = "task_id")
    private Integer taskId;
    @Column(name = "pre_task_unit_id")
    private Integer parentTaskUnitId;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pre_task_unit_id", insertable = false, updatable = false)
    private TaskUnit parentTaskUnit;

    @OneToOne(mappedBy = "parentTaskUnit")
    private TaskUnit subTaskUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", insertable = false, updatable = false)
    private Task task;
}
