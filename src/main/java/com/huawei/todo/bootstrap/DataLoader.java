package com.huawei.todo.bootstrap;

import com.huawei.todo.entity.Authority;
import com.huawei.todo.entity.ConfirmationToken;
import com.huawei.todo.entity.Task;
import com.huawei.todo.entity.User;
import com.huawei.todo.repository.AuthorityRepository;
import com.huawei.todo.repository.ConfirmationTokenRepository;
import com.huawei.todo.repository.TaskRepository;
import com.huawei.todo.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author sumutella
 * @time 10:18 PM
 * @since 12/15/2019, Sun
 */
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final AuthorityRepository authorityRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public DataLoader(UserRepository userRepository, TaskRepository taskRepository, AuthorityRepository authorityRepository,
                      ConfirmationTokenRepository confirmationTokenRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.authorityRepository = authorityRepository;
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        userRepository.save(initData());

        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setId(1);
        confirmationToken.setUserId(1);
        confirmationToken.setConfirmationToken("abcdef");
        confirmationTokenRepository.save(confirmationToken);


        Authority authority = new Authority();
        authority.setUsername("sumutella");
        authority.setAuthority("ROLE_VERIFIED");
        authorityRepository.save(authority);
    }

    public User initData(){


        User user = new User();
        user.setId(1);
        user.setUsername("sumutella");
        user.setEnabled(true);
        user.setPassword("{noop}123");
        user.setEmail("sumutella@gmail.com");
        user.setFullName("Semir Umut Kurt");



        Task task = new Task();
        task.setId(1);
        task.setUserId(1);
        task.setName("create bootstrap data loader");



        Task task1 = new Task();
        task1.setId(2);
        task1.setUserId(1);
        task1.setName("add task");


        Task task2 = new Task();
        task2.setId(3);
        task2.setUserId(1);
        task2.setName("delete task");


        user.getTasks().add(task);
        user.getTasks().add(task1);
        user.getTasks().add(task2);

        return user;
    }
}
