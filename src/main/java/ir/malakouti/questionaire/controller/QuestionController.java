package ir.malakouti.questionaire.controller;

import ir.malakouti.questionaire.convertor.QuestionConvertor;
import ir.malakouti.questionaire.model.dto.QuestionDto;
import ir.malakouti.questionaire.model.dto.QuestionOutputDto;
import ir.malakouti.questionaire.model.dto.QuestionRequestDto;
import ir.malakouti.questionaire.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
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
                        .message("Question added successfully...")
                        .build());
    }

    @PutMapping("question/{id}")
    public ResponseEntity<ResponseResult<QuestionOutputDto>>
    updateQuestion(@PathVariable("id") Integer id, @RequestBody QuestionDto questionDto) {
        QuestionDto result = questionService.updateQuestion(id, questionDto);
        QuestionOutputDto questionOutputDto = questionConvertor.InputDtoToOutputDto(result);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseResult.<QuestionOutputDto>builder()
                        .code(0)
                        .data(questionOutputDto)
                        .message("Question updated successfully...")
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseResult<QuestionOutputDto>> getQuestionById(@PathVariable("id") Integer id) {
        QuestionDto result = questionService.getQuestionById(id);
        QuestionOutputDto questionOutputDto = questionConvertor.InputDtoToOutputDto(result);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseResult.<QuestionOutputDto>builder()
                        .code(0)
                        .data(questionOutputDto)
                        .message("Question loaded successfully...")
                        .build());
    }

    @GetMapping
    public ResponseEntity<ResponseResult<QuestionOutputDto>> loadAllQuestion(){
        List<QuestionOutputDto> questionOutputDtos = new ArrayList<>();
        for (QuestionDto questions : questionService.getAllQuestions()) {
            questionOutputDtos.add(questionConvertor.InputDtoToOutputDto(questions));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseResult.<QuestionOutputDto>builder()
                        .code(0)
                        .dataList(questionOutputDtos)
                        .message("Question loaded successfully...")
                        .build());
    }


}
