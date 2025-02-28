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
// Error Code follow Entity
// 1xxx USER
// 3xxx POST
// 4xxx UNIVERSITY
// 5xxx TEACHER
// 6xxx GRADUATE


// 1XXX INVALID
public enum ErrorApp {
    //EXISTED
    USER_EXISTED(2001,"User existed ! Pls try again with username and email",HttpStatus.BAD_REQUEST),
    TEACHER_ALREADY (5001,"User is already a teacher",HttpStatus.BAD_REQUEST),
    //INVALID
    USERNAME_INVALID(1002,"User has at least {min} character",HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1003,"Password has at least {min} character", HttpStatus.BAD_REQUEST),
    EMAIL_NOT_BLANK(1004,"Email must have to send code authorize",HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(1005,"Email invalid",HttpStatus.BAD_REQUEST),
    // upload
    FILE_UPLOAD_LARGE(1006,"File upload to large, try again",HttpStatus.BAD_REQUEST),
    //DUPLICATE_ENTRY
    DUPLICATE_ENTRY(1007,"Duplicate entry because field is unique",HttpStatus.BAD_REQUEST),
    //
    FULL_GRADUATE(6099,"Everyone have maximum 2 graduates",HttpStatus.BAD_REQUEST),
    //NOT_FOUND
    USER_NOT_FOUND(2004,"User Not found", HttpStatus.NOT_FOUND),
    POST_NOT_FOUND(3004,"Post Not found", HttpStatus.NOT_FOUND),
    UNIVERSITY_NOT_FOUND( 4004,"University Not Found",HttpStatus.NOT_FOUND),
    ROLES_NOT_FOUND(6004,"Roles Not Found",HttpStatus.NOT_FOUND),
    EXPERIENCE_NOT_FOUND(7004,"Experience Not Found",HttpStatus.NOT_FOUND),
    REFRESH_TOKEN_NOT_FOUND(8006,"REFRESH_TOKEN_NOT_FOUND",HttpStatus.BAD_REQUEST),
    //FORBIDDEN
    USER_PASSWORD_WRONG(2005,"User or Password Wrong",HttpStatus.UNAUTHORIZED),
    AUTHORIZATION_DENIED(2006,"You don't have permission",HttpStatus.FORBIDDEN),
    UNAUTHORIZED(2007,"Unauthorized",HttpStatus.UNAUTHORIZED),
    PERMISSION_NOT_FOUND(2004,"Permission not found", HttpStatus.NOT_FOUND),
    DOB_INVALID(2008,"User has been at least {min} years old",HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION(999,"Unknown ERROR",HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY_ERROR(1001,"Invalid key error",HttpStatus.BAD_REQUEST),
    //OTHERS
    TIMEOUT_REQUEST(5000,"Something wrong with sever",HttpStatus.BAD_GATEWAY)

    ;
    int code;
    String message;
    HttpStatus httpStatus;


}
