package com.spring_app.sandbox.domain.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import com.spring_app.sandbox.domain.entity.TaskPriority;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// this object is what rest api expects to receive when creating a new task, it will be converted to a Task object by the controller
// why not use the object we created in the service layer? The service layer owns that object, it is not the responsibility of the controller to know about it, 
// the controller should only know about the objects that are relevant to the api, in this case, the CreateTaskRequestDto, 
// if we use the Task object in the controller, we are exposing the internal implementation of the service layer to the controller, 
// which is not a good practice, it also makes it harder to change the service layer implementation without affecting the controller, 
// by using a separate DTO for the controller we can decouple the controller from the service layer and make it easier to 
// change the service layer implementation without affecting the controller

public record CreateTaskRequestDto(
    @NotBlank(message = TITLE_LENGTH_MESSAGE)
    @Length(max = 255, message = TITLE_LENGTH_MESSAGE)
    String title,
    @Length(max = 1000, message = DESCRIPTION_LENGTH_MESSAGE)
    @Nullable
    String description,
    @Nullable
    @FutureOrPresent(message = DUE_DATE_MESSAGE)
    LocalDate dueDate,
    @NotNull(message = PRIORITY_MESSAGE)
    TaskPriority priority
) {
    private static final int MAX_TITLE_LENGTH = 255;
    private static final String TITLE_LENGTH_MESSAGE = "Title must be between 1 and " + MAX_TITLE_LENGTH + " characters";
    private static final int MAX_DESCRIPTION_LENGTH = 1000;
    private static final String DESCRIPTION_LENGTH_MESSAGE = "Description must be less than " + MAX_DESCRIPTION_LENGTH + " characters";
    private static final String DUE_DATE_MESSAGE = "Due date must be in the present or future";     
    private static final String PRIORITY_MESSAGE = "Priority is required";  



}
