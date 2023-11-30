package by.nikita.spring.http.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(basePackages = "by.nikita.spring.http.controller")
public class ControllerExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception){
        log.error("Failed to return response", exception);
        return "error/error500";
    }
}
