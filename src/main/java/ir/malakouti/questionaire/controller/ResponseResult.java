package ir.malakouti.questionaire.controller;


import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult<T> {
    private Integer code;
    private T data;
    private List<T> dataList;
    private String message;
}
