package com.huawei.todo.repository;

import com.huawei.todo.entity.TaskUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author sumutella
 * @time 5:39 PM
 * @since 12/15/2019, Sun
 */
public interface TaskUnitRepository extends JpaRepository<TaskUnit, Integer> {
    List<TaskUnit> findByTask_Id(Integer taskId);
}
