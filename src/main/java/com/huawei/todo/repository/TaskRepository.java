package com.huawei.todo.repository;

import com.huawei.todo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author sumutella
 * @time 5:38 PM
 * @since 12/15/2019, Sun
 */
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByUser_Id(Integer userId);
}
