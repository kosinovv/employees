package com.kosinov.employees.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosinov.employees.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class EmployeeCachedRepository {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Optional<Employee> findByTabNum(String tabNum) {
        String stringValue = redisTemplate.opsForValue().get(tabNum + "_emp");

        if (stringValue == null) {
            return Optional.empty();
        }

        try {
            return Optional.of(objectMapper.readValue(stringValue, Employee.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Employee save(Employee employee) {
        try {
            redisTemplate.opsForValue().set(employee.getTabNum() + "_emp", objectMapper.writeValueAsString(employee));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return employee;
    }

    @Scheduled(fixedDelay = 1L)
    public void clearAll() {
        Set<String> keys = redisTemplate.keys("*");
        if (keys == null) {
            return;
        }

        System.out.printf("Из кэша будет удално %s записей", keys.size());

        redisTemplate.delete(keys);
    }
}

