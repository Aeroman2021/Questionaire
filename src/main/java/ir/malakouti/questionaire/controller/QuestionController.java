package ir.malakouti.questionaire.controller;

import ir.malakouti.questionaire.controller.api.core.ServiceResult;
import ir.malakouti.questionaire.convertor.QuestionsConvertor;
import ir.malakouti.questionaire.model.dto.QuestionDto;
import ir.malakouti.questionaire.model.dto.QuestionRequestDto;
import ir.malakouti.questionaire.service.QuestionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionServiceImpl questionService;
    private final QuestionsConvertor questionConvertor;

    @PostMapping("/save")
    public ResponseEntity<ServiceResult<QuestionDto>>
    saveQuestion(@RequestBody QuestionRequestDto questionRequestDto) {
        QuestionDto result = questionService.save(questionRequestDto);
        return ResponseEntity.ok(ServiceResult.success(result));
    }

    @PutMapping("question/{id}")
    public ResponseEntity<ServiceResult<QuestionDto>>
    updateQuestion(@PathVariable("id") Integer id, @RequestBody QuestionDto questionDto) {
        QuestionDto result = questionService.update(id, questionDto);
        return ResponseEntity.ok(ServiceResult.success(result));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ServiceResult<QuestionDto>> deleteQuestion(@PathVariable("id") Integer id) {
        QuestionDto result = questionService.findById(id);
        questionService.deleteById(id);
        return ResponseEntity.ok(ServiceResult.success(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResult<QuestionDto>> getQuestionById(@PathVariable("id") Integer id) {
        QuestionDto result = questionService.findById(id);
        return ResponseEntity.ok(ServiceResult.success(result));
    }

    @GetMapping
    public ResponseEntity<ServiceResult<QuestionDto>> loadAllQuestion(){
        List<QuestionDto> questionDtos = new ArrayList<>();
        for (QuestionDto questions : questionService.findAll()) {
            questionDtos.add(questions);
        }
        return ResponseEntity.ok(ServiceResult.success(questionDtos));
    }


}
