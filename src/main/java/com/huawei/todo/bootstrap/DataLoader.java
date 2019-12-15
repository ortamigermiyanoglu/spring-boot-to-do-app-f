package com.huawei.todo.bootstrap;

import com.huawei.todo.mapper.v1.AuthorityMapper;
import com.huawei.todo.mapper.v1.TaskMapper;
import com.huawei.todo.mapper.v1.UserMapper;
import com.huawei.todo.repository.AuthorityRepository;
import com.huawei.todo.repository.TaskRepository;
import com.huawei.todo.repository.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author sumutella
 * @time 10:18 PM
 * @since 12/15/2019, Sun
 */
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private final TaskMapper taskMapper = Mappers.getMapper(TaskMapper.class);
    private final AuthorityMapper authorityMapper = Mappers.getMapper(AuthorityMapper.class);


    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final AuthorityRepository authorityRepository;

    public DataLoader(UserRepository userRepository, TaskRepository taskRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

    }
}
