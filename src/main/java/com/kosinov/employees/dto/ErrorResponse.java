package com.kosinov.employees.dto;

import com.kosinov.employees.model.InternalErrorStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private InternalErrorStatus status;

    private String message;
}
