package com.huawei.todo.repository;

import com.huawei.todo.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sumutella
 * @time 5:39 PM
 * @since 12/15/2019, Sun
 */
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
