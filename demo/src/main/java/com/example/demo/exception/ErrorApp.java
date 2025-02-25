package com.example.demo.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public enum ErrorApp {
    USER_EXISTED(2001,"User existed",HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(2002,"User has at least {min} character",HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(2003,"Password has at least {min} character", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(2004,"User Not found", HttpStatus.NOT_FOUND),
    POST_NOT_FOUND(3004,"Post Not found", HttpStatus.NOT_FOUND),
    UNIVERSITY_NOT_FOUND( 4004,"University Not Found",HttpStatus.NOT_FOUND),
    USER_PASSWORD_WRONG(2005,"User or Password Wrong",HttpStatus.UNAUTHORIZED),
    AUTHORIZATION_DENIED(2006,"You don't have permission",HttpStatus.FORBIDDEN),
    UNAUTHORIZED(2007,"Unauthorized",HttpStatus.UNAUTHORIZED),
    PERMISSION_NOT_FOUND(2004,"Permission not found", HttpStatus.NOT_FOUND),
    DOB_INVALID(2008,"User has been at least {min} years old",HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION(999,"Unknown ERROR",HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY_ERROR(1001,"Invalid key error",HttpStatus.BAD_REQUEST),
    TIMEOUT_REQUEST(5000,"Something wrong with sever",HttpStatus.BAD_GATEWAY)
    ;
    int code;
    String message;
    HttpStatus httpStatus;


}
