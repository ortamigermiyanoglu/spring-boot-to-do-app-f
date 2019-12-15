package com.huawei.todo.repository;

import com.huawei.todo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sumutella
 * @time 5:38 PM
 * @since 12/15/2019, Sun
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmailIgnoreCase(String email);
}
