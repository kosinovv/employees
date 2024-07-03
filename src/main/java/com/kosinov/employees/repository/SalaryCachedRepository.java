package com.kosinov.employees.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosinov.employees.model.SalaryPayment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class SalaryCachedRepository {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Optional<SalaryPayment> findById(Integer id) {
        String stringValue = redisTemplate.opsForValue().get(String.valueOf(id));

        if (stringValue == null) {
            return Optional.empty();
        }

        try {
            return Optional.of(objectMapper.readValue(stringValue, SalaryPayment.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public SalaryPayment save(SalaryPayment salaryPayment) {
        try {
            redisTemplate.opsForValue().set(String.valueOf(salaryPayment.getId()), objectMapper.writeValueAsString(salaryPayment));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return salaryPayment;
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


