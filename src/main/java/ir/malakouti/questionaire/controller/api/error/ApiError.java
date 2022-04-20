package ir.malakouti.questionaire.controller.api.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;


@Setter
@Getter
@Builder
@AllArgsConstructor
public class ApiError {
    private HttpStatus httpStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timeStamp;
    private String message;
    private String debugMessage;
    private List<ApiSubError> subErrors;

    public ApiError() {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus httpStatus) {
        this();
        this.httpStatus = httpStatus;
    }

    public ApiError(HttpStatus httpStatus, Throwable e) {
        this();
        this.httpStatus = httpStatus;
        this.message = "unexpected error";
        this.debugMessage = e.getLocalizedMessage();
    }

    public ApiError(HttpStatus httpStatus,String message, Throwable e) {
        this();
        this.httpStatus = httpStatus;
        this.message = message;
        this.debugMessage = e.getLocalizedMessage();
    }

    public ApiError(HttpStatus httpStatus,String message) {
        this(httpStatus);
        this.message = message;
    }
}
