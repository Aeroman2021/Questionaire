package ir.malakouti.questionaire.controller;

import ir.malakouti.questionaire.controller.api.core.ServiceResult;
import ir.malakouti.questionaire.convertor.QuestionConvertor;
import ir.malakouti.questionaire.model.dto.QuestionDto;
import ir.malakouti.questionaire.model.dto.QuestionOutputDto;
import ir.malakouti.questionaire.model.dto.QuestionRequestDto;
import ir.malakouti.questionaire.service.QuestionService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<ServiceResult<QuestionOutputDto>>
    saveQuestion(@RequestBody QuestionRequestDto questionRequestDto) {
        QuestionDto result = questionService.saveQuestion(questionRequestDto);
        QuestionOutputDto questionOutputDto = questionConvertor.InputDtoToOutputDto(result);
        return ResponseEntity.ok(ServiceResult.success(questionOutputDto));
    }

    @PutMapping("question/{id}")
    public ResponseEntity<ServiceResult<QuestionOutputDto>>
    updateQuestion(@PathVariable("id") Integer id, @RequestBody QuestionDto questionDto) {
        QuestionDto result = questionService.updateQuestion(id, questionDto);
        QuestionOutputDto questionOutputDto = questionConvertor.InputDtoToOutputDto(result);
        return ResponseEntity.ok(ServiceResult.success(questionOutputDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResult<QuestionOutputDto>> getQuestionById(@PathVariable("id") Integer id) {
        QuestionDto result = questionService.getQuestionById(id);
        QuestionOutputDto questionOutputDto = questionConvertor.InputDtoToOutputDto(result);
        return ResponseEntity.ok(ServiceResult.success(questionOutputDto));
    }

    @GetMapping
    public ResponseEntity<ServiceResult<QuestionOutputDto>> loadAllQuestion(){
        List<QuestionOutputDto> questionOutputDtos = new ArrayList<>();
        for (QuestionDto questions : questionService.getAllQuestions()) {
            questionOutputDtos.add(questionConvertor.InputDtoToOutputDto(questions));
        }
        return ResponseEntity.ok(ServiceResult.success(questionOutputDtos));
    }


}
