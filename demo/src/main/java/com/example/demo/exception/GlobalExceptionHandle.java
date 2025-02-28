package com.example.demo.exception;

import com.example.demo.dto.response.ApiResponse;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandle {

    private static final String ATTRIBUTE_MIN = "min";

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException exception) {
        log.warn("ERR", exception);
        ErrorApp error = ErrorApp.UNCATEGORIZED_EXCEPTION;
        ApiResponse response = new ApiResponse();
        response.setCode(error.getCode());
        response.setMessage(error.getMessage());
        return ResponseEntity.status(error.getHttpStatus()).body(response);
    }

    @ExceptionHandler(value = AuthorizationDeniedException.class)
    ResponseEntity<ApiResponse> handleAuthorizationDeniedException(AuthorizationDeniedException exception) {
        ErrorApp error = ErrorApp.AUTHORIZATION_DENIED;
        ApiResponse response = new ApiResponse();
        response.setCode(error.getCode());
        response.setMessage(error.getMessage());
        return ResponseEntity.status(error.getHttpStatus()).body(response);
    }
    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    ResponseEntity<ApiResponse> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException exception) {
        ErrorApp error = ErrorApp.FILE_UPLOAD_LARGE;
        ApiResponse response = new ApiResponse();
        response.setCode(error.getCode());
        response.setMessage(error.getMessage());
        return ResponseEntity.status(error.getHttpStatus()).body(response);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    ResponseEntity<ApiResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        ErrorApp error = ErrorApp.DUPLICATE_ENTRY;
        log.warn("DAY LA HANDLE",exception);
        ApiResponse response = new ApiResponse();
        response.setCode(error.getCode());
        response.setMessage(error.getMessage());
        return ResponseEntity.status(error.getHttpStatus()).body(response);
    }
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception) {
        ErrorApp error = exception.getError();
        ApiResponse response = new ApiResponse();
        response.setCode(error.getCode());
        response.setMessage(error.getMessage());
        return ResponseEntity.status(error.getHttpStatus()).body(response);
    }

    @ExceptionHandler(value = RestClientException.class)
    ResponseEntity<ApiResponse> handlingRestClientException(RestClientException exception){
        ErrorApp error = ErrorApp.TIMEOUT_REQUEST;
        ApiResponse response = new ApiResponse();
        response.setCode(error.getCode());
        response.setMessage(error.getMessage());
        return ResponseEntity.status(error.getHttpStatus()).body(response);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handleArgumentNotvaidtation(MethodArgumentNotValidException notvalid) {
        String enumErrorAppKey = notvalid.getFieldError().getDefaultMessage();
        Map<String, Objects> atrributes = null;
        ErrorApp error = ErrorApp.INVALID_KEY_ERROR;
        ApiResponse response = new ApiResponse();
        try {

            var constraintViolation = notvalid.getBindingResult().getAllErrors().get(0).unwrap(ConstraintViolation.class);
            atrributes = constraintViolation.getConstraintDescriptor().getAttributes();
            error = ErrorApp.valueOf(enumErrorAppKey);

        } catch (IllegalArgumentException e) {

        }
        response.setCode(error.getCode());
        response.setMessage(Objects.nonNull(atrributes)
                ? MappingMessage(error.getMessage(), atrributes)
                : error.getMessage());

        return ResponseEntity.status(error.getHttpStatus()).body(response);
    }

    private String MappingMessage(String messageDefault, Map<String, Objects> atrributes) {
        String valueMin = String.valueOf(atrributes.get(ATTRIBUTE_MIN));

        return messageDefault.replace("{" + ATTRIBUTE_MIN + "}", valueMin);
    }
}
