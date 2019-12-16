package com.huawei.todo.bootstrap;

import com.huawei.todo.entity.*;
import com.huawei.todo.repository.AuthorityRepository;
import com.huawei.todo.repository.ConfirmationTokenRepository;
import com.huawei.todo.repository.TaskRepository;
import com.huawei.todo.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author sumutella
 * @time 10:18 PM
 * @since 12/15/2019, Sun
 */
// this component populates data on embedded h2 db if you want to use the program without registration
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
        User user = initUserData();
        List<Task> tasks = initTaskData();
        tasks.get(0).getTaskUnits().addAll(initTaskUnitData());
        user.getTasks().addAll(initTaskData());


        userRepository.save(user);
        taskRepository.saveAll(tasks);
        authorityRepository.save(initAuthorityData());
        confirmationTokenRepository.save(initConfirmationTokenData());


    }

    private User initUserData(){


        User user = new User();
        user.setId(1);
        user.setUsername("sumutella");
        user.setEnabled(true);
        user.setPassword("{noop}123");
        user.setEmail("sumutella@gmail.com");
        user.setFullName("Semir Umut Kurt");

        return user;
    }

    private List<Task> initTaskData(){
        List<Task> tasks = new ArrayList<>();

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
        tasks.add(task);
        tasks.add(task1);
        tasks.add(task2);

        return tasks;

    }

    private Authority initAuthorityData(){
        Authority authority = new Authority();
        authority.setUsername("sumutella");
        authority.setAuthority("ROLE_VERIFIED");
        return authority;
    }


    private ConfirmationToken initConfirmationTokenData(){
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setId(1);
        confirmationToken.setUserId(1);
        confirmationToken.setConfirmationToken("abcdef");
        return confirmationToken;
    }

    private List<TaskUnit> initTaskUnitData(){

        List<TaskUnit> taskUnits = new ArrayList<>();

        TaskUnit taskUnit = new TaskUnit();
        taskUnit.setId(1);
        taskUnit.setName("list task unit");
        taskUnit.setDescription("create bootstrap");
        taskUnit.setStatus("Not Complete");
        taskUnit.setDeadline(new Date(2019,12,16));
        taskUnit.setTaskId(1);

        TaskUnit taskUnit1 = new TaskUnit();
        taskUnit1.setId(2);
        taskUnit1.setName("create html file");
        taskUnit1.setStatus("Not Complete");
        taskUnit1.setDescription("use bootstrap javascript html");
        taskUnit1.setDeadline(new Date(2019,12,17));
        taskUnit1.setTaskId(1);


        TaskUnit taskUnit2 = new TaskUnit();
        taskUnit2.setId(3);
        taskUnit2.setName("create controller");
        taskUnit2.setStatus("Not Complete");
        taskUnit2.setDescription("add attributes");
        taskUnit2.setDeadline(new Date(2019,12,18));
        taskUnit2.setTaskId(1);

        taskUnits.add(taskUnit);
        taskUnits.add(taskUnit1);
        taskUnits.add(taskUnit2);


        return taskUnits;
    }
}
