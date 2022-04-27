package ir.malakouti.questionaire.controller.api.core;


import ir.malakouti.questionaire.controller.api.error.ApiError;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceResult<T> {
    private int code;
    private T data;
    private List<T> dataList;
    private String message;
    private ApiError apiError;

    public static <T> ServiceResult<T> success(T data){
        return ServiceResult.<T>builder()
                .code(ErrorStatus.SUCCESS.getCode())
                .data(data)
                .dataList(null)
                .message(ErrorStatus.SUCCESS.getMessage())
                .apiError(null)
                .build();
    }

    public static <T> ServiceResult<T> success(List<T> dataList){
        return ServiceResult.<T>builder()
                .code(ErrorStatus.SUCCESS.getCode())
                .data(null)
                .dataList(dataList)
                .message(ErrorStatus.SUCCESS.getMessage())
                .apiError(null)
                .build();
    }

    public static <T> ServiceResult<T> success(){
        return ServiceResult.<T>builder()
                .code(ErrorStatus.SUCCESS.getCode())
                .data(null)
                .dataList(null)
                .message(ErrorStatus.SUCCESS.getMessage())
                .apiError(null)
                .build();
    }

    public static <T> ServiceResult<T> notFound(T data){
        return ServiceResult.<T>builder()
                .code(ErrorStatus.NOT_FOUND.getCode())
                .data(data)
                .dataList(null)
                .message(ErrorStatus.NOT_FOUND.getMessage())
                .apiError(null)
                .build();
    }

    public static <T> ServiceResult<T> fail(HttpStatus httpStatus,ErrorStatus errorStatus){
        return ServiceResult.<T>builder()
                .code(errorStatus.getCode())
                .data(null)
                .dataList(null)
                .message(errorStatus.getMessage())
                .apiError(null)
                .build();
    }

    public static <T> ServiceResult<Void> fail(ApiError apiError){
        return ServiceResult.<Void>builder()
                .code(apiError.getHttpStatus().value())
                .data(null)
                .dataList(null)
                .message(apiError.getMessage())
                .apiError(apiError)
                .build();
    }
}









