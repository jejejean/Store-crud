package com.Spring_Security_Back.exceptions;

import com.Spring_Security_Back.utils.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Map<String, String> fieldsErrorsMap = new HashMap<>();


    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> badRequestExceptionHandler(Exception ex, HttpServletRequest request) {
        return handleException(ex, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalServerError.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> internalServerErrorExceptionHandler(Exception ex, HttpServletRequest request) {
        return handleException(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleBadCredentialsException(Exception ex, HttpServletRequest request) {
        Response response = new Response("El nombre de usuario y/o la contraseña no son válidos", 400);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String fieldName = fieldError.getField();
            String errorMessage = fieldError.getDefaultMessage();
            fieldsErrorsMap.put(fieldName, errorMessage);
        }
        return new ResponseEntity<>(fieldsErrorsMap, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> handleException(Exception ex, HttpServletRequest request, HttpStatus status) {
        ExceptionInfo exceptionInfo = buildExceptionInfo(ex, request, status);
        return new ResponseEntity<>(exceptionInfo, status);
    }

    private ExceptionInfo buildExceptionInfo(Exception ex, HttpServletRequest request, HttpStatus status) {
        ExceptionInfo exceptionInfo = new ExceptionInfo();
        exceptionInfo.setTimestamp(LocalDateTime.now());
        exceptionInfo.setStatusCode(status.value());
        exceptionInfo.setStatusName(status.getReasonPhrase());
        exceptionInfo.setMessage(ex.getMessage());
        exceptionInfo.setUriRequested(request.getRequestURI());
        return exceptionInfo;
    }

    // Manejar errores de validación de campos de la entidad
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        ex.getConstraintViolations().forEach(violation ->
                errors.put(violation.getPropertyPath().toString(), violation.getMessage())
        );

        response.put("message", "Errores en la validación de datos.");
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("path", request.getRequestURI());
        response.put("errors", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, HttpServletRequest request) {
        System.out.println("⛔ Manejo de NotFoundException activado: " + ex.getMessage()); // Log de depuración

        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("path", request.getRequestURI());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
