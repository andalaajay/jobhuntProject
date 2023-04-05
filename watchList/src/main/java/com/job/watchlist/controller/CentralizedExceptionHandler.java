package com.job.watchlist.controller;

import com.job.watchlist.exception.JobAlreadyExistException;
import com.job.watchlist.exception.NoJobFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class CentralizedExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(CentralizedExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)

    @ExceptionHandler({ConstraintViolationException.class,
            MethodArgumentNotValidException.class, JobAlreadyExistException.class
    })


    public String jobAlreadyExists(Exception e) {
        log.info("job already exist ", e);
        String msg = e.getMessage();
        return msg;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoJobFoundException.class)
    public String noJobFound(Exception e) {
        log.info("no job found ", e);
        String msg = e.getMessage();
        return msg;
    }
}
