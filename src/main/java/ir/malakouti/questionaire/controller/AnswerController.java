package ir.malakouti.questionaire.controller;

import ir.malakouti.questionaire.controller.api.core.ServiceResult;
import ir.malakouti.questionaire.convertor.AnswersConvertor;
import ir.malakouti.questionaire.model.dto.AnswerDto;
import ir.malakouti.questionaire.model.dto.AnswerRequestDto;
import ir.malakouti.questionaire.service.impl.AnswerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/answers")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerServiceImpl answerService;
    private final AnswersConvertor answerConvertor;

    @PostMapping("/save")
    public ResponseEntity<ServiceResult<AnswerDto>> saveAnswer
            (@RequestBody AnswerRequestDto answerRequestDto) {
        AnswerDto result = answerService.save(answerRequestDto);
        return ResponseEntity.ok(ServiceResult.success(result));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ServiceResult<AnswerDto>> editAnswer
            (@PathVariable("id") Integer id, @RequestBody AnswerDto answerDto) {
        AnswerDto result = answerService.update(id, answerDto);
        return ResponseEntity.ok(ServiceResult.success(result));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ServiceResult<AnswerDto>> deleteAnswerById(@PathVariable("id") Integer id) {
        AnswerDto result = answerService.findById(id);
        answerService.DeleteById(id);
        return ResponseEntity.ok(ServiceResult.success(result));
    }


    @GetMapping("/answer/{id}")
    public ResponseEntity<ServiceResult<AnswerDto>> getAnswerById(@PathVariable("id") Integer id) {
        AnswerDto result = answerService.findById(id);
        return ResponseEntity.ok(ServiceResult.success(result));
    }


    @GetMapping
    public ResponseEntity<ServiceResult<AnswerDto>> getAllAnswers() {
        List<AnswerDto> answerDtos = new ArrayList<>();
        for (AnswerDto tempAnswerDto : answerService.findAll()) {
            answerDtos.add(tempAnswerDto);
        }
        return ResponseEntity.ok(ServiceResult.success(answerDtos));
    }


}
