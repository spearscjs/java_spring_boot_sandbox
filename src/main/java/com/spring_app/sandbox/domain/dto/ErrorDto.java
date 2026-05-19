package com.spring_app.sandbox.domain.dto;

/**
 * Simple error payload returned to API clients.
 *
 * Use a `@ControllerAdvice` with `@ExceptionHandler` methods to map exceptions
 * to `ErrorDto` (or a Problem/structured error) responses with appropriate HTTP
 * status codes. Handle validation and domain-specific exceptions explicitly,
 * log internal details server-side, and avoid returning stack traces to clients.
 */
public record ErrorDto(String error) {

}