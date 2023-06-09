package youth.common.hadlers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class BaseExceptionHandler {
   @ExceptionHandler(BindException.class)
   public ResponseEntity<ErrorResponse> handleBindExceptions(BindException e) {
      log.error("Bind Exceptions :: {}", e.getMessage());

      List<String> errors = e.getBindingResult().getFieldErrors()
            .stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList());

      return ResponseEntity.badRequest().body(
            new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errors.toString())
      );
   }

   @ExceptionHandler(Exception.class)
   public ResponseEntity<ErrorResponse> handleException(Exception e) {
      log.error("Internal Server Error :: {}", e.getMessage());

      e.printStackTrace();

      return ResponseEntity.internalServerError().body(
            new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage())
      );
   }

   @Getter(value = AccessLevel.PUBLIC)
   public static class ErrorResponse {
      private int code;
      private String message;
      @JsonDeserialize(using = LocalDateTimeDeserializer.class)
      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
      private LocalDateTime time;

      public ErrorResponse(int code, String message) {
         this.code = code;
         this.message = message;
         this.time = LocalDateTime.now();
      }
   }
}
