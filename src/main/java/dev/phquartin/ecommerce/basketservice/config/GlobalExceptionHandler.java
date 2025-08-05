package dev.phquartin.ecommerce.basketservice.config;

import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm:ss - dd/MM/yyyy");

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handleException(Exception exception, HttpServletRequest request) {
        return new ResponseEntity<>(new ErroResponse(
                exception.getMessage(),
                request.getRequestURI(),
                exception.getClass().getSimpleName(),
                LocalDateTime.now().format(dateFormatter),
                "500"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(FeignException.BadRequest.class)
    public ResponseEntity<ErroResponse> handleBadRequestException(FeignException exception, HttpServletRequest request) {
        return new ResponseEntity<>(new ErroResponse(
                "Invalid request for the API: " + exception.getMessage(),
                request.getRequestURI(),
                exception.getClass().getSimpleName(),
                LocalDateTime.now().format(dateFormatter),
                "400"),
                HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroResponse> handleIllegalArgumentException(IllegalArgumentException exception, HttpServletRequest request) {
        return new ResponseEntity<>(new ErroResponse(
                exception.getMessage(),
                request.getRequestURI(),
                exception.getClass().getSimpleName(),
                LocalDateTime.now().format(dateFormatter),
                "400"),
                HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErroResponse> handleNoResourceFoundException(NoResourceFoundException exception, HttpServletRequest request) {
        return new ResponseEntity<>(new ErroResponse(
                "Page not Found :(",
                request.getRequestURI(),
                exception.getClass().getSimpleName(),
                LocalDateTime.now().format(dateFormatter),
                "404"),
                HttpStatus.NOT_FOUND);
    }
}
