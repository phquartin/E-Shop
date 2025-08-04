package dev.phquartin.ecommerce.basketservice.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        try {
            ObjectMapper mapper = new ObjectMapper();
            String response = exception.getMessage();

            Matcher matcher = Pattern.compile("(\\[\\{.*}])").matcher(response);
            if (!matcher.find()) throw new IllegalArgumentException("JSON não encontrado");
            String json = matcher.group(1);

            List<Map<String, Object>> data = mapper.readValue(json, new TypeReference<>() {});
            Map<String, Object> first = data.getFirst();

            String message = first.get("message").toString();
            int end = message.indexOf("\" matching:");
            String result = (end > 0) ? message.substring(0, end) : message;

            return new ResponseEntity<>(
                    new ErroResponse(
                            result,
                            request.getRequestURI(),
                            first.get("name").toString(),
                            LocalDateTime.now().format(dateFormatter),
                            "400"
                    ),
                    HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ErroResponse(
                            exception.getLocalizedMessage(),
                            request.getRequestURI(),
                            exception.getClass().getSimpleName(),
                            LocalDateTime.now().format(dateFormatter),
                            "400"
                    ),
                    HttpStatus.BAD_REQUEST
            );
        }
    }


}
