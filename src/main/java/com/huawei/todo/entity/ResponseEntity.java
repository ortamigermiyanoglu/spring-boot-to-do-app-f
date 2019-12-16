package com.huawei.todo.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sumutella
 * @time 11:19 AM
 * @since 12/7/2019, Sat
 */
@Setter
@Getter
@AllArgsConstructor
public class ResponseEntity<T> {
    private String status;
    private List<T> data = new ArrayList<>();
}
