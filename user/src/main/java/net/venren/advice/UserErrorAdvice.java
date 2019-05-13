package net.venren.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
@EnableWebMvc
public class UserErrorAdvice {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({org.hibernate.exception.ConstraintViolationException.class})
    public void handle(org.hibernate.exception.ConstraintViolationException e) {}
}
