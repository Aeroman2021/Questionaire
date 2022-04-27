package ir.malakouti.questionaire.controller.api.error;

import ir.malakouti.questionaire.controller.api.core.ServiceResult;
import ir.malakouti.questionaire.exception.AnswerException;
import ir.malakouti.questionaire.exception.CustomerException;
import ir.malakouti.questionaire.exception.EntityNotFoundException;
import ir.malakouti.questionaire.exception.QuestionException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AnswerException.class)
    protected ResponseEntity<ServiceResult<Void>> handleAccountException(AnswerException ex) {
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(CustomerException.class)
    protected ResponseEntity<ServiceResult<Void>> handleAccountException(CustomerException ex) {
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(QuestionException.class)
    protected ResponseEntity<ServiceResult<Void>> handleAccountException(QuestionException ex) {
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ServiceResult<Void>> handleAccountException(EntityNotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    public ResponseEntity<ServiceResult<Void>> buildResponseEntity(ApiError apiError) {
        var sr = ServiceResult.fail(apiError);
        return new ResponseEntity<>(sr,apiError.getHttpStatus());
    }
}
