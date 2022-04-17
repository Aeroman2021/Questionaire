package ir.malakouti.questionaire.controller;

import ir.malakouti.questionaire.convertor.QuestionConvertor;
import ir.malakouti.questionaire.model.dto.CustomerOutputDto;
import ir.malakouti.questionaire.model.dto.QuestionDto;
import ir.malakouti.questionaire.model.dto.QuestionOutputDto;
import ir.malakouti.questionaire.model.dto.QuestionRequestDto;
import ir.malakouti.questionaire.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionConvertor questionConvertor;

    @PostMapping("/save")
    public ResponseEntity<ResponseResult<QuestionOutputDto>>
    saveQuestion(@RequestBody QuestionRequestDto questionRequestDto) {
        QuestionDto result = questionService.saveQuestion(questionRequestDto);
        QuestionOutputDto questionOutputDto = questionConvertor.InputDtoToOutputDto(result);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseResult.<QuestionOutputDto>builder()
                        .code(0)
                        .data(questionOutputDto)
                        .message("Customer registered successfully...")
                        .build());
    }

    

}
