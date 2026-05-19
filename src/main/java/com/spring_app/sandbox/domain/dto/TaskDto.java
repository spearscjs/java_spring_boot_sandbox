package com.spring_app.sandbox.domain.dto;

// making generic dto for response on different endpoints

import java.time.LocalDate;
import java.util.UUID;

import com.spring_app.sandbox.domain.entity.TaskPriority;
import com.spring_app.sandbox.domain.entity.TaskStatus;

// fine for this but we are coupling the endpoints to the same response object, 
// if we want to change the response for one endpoint we have to change it for all endpoints
// why no validation annotations? This is not a request object, this is a response object, we are not validating the response, 
// we are just sending it back to the client, the validation is done on the request object, which is the CreateTaskRequestDto, 
// we are validating the input from the client, not the output to the client
public record TaskDto(
    UUID id,
    String title,
    String description,
    LocalDate dueDate,
    TaskPriority priority,
    TaskStatus status
) {

}
