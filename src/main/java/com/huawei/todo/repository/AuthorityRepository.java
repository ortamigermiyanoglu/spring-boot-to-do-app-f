package com.huawei.todo.repository;

import com.huawei.todo.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sumutella
 * @time 5:40 PM
 * @since 12/15/2019, Sun
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
    Authority findByUsernameIgnoreCase(String username);
}
