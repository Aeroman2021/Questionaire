package ir.malakouti.questionaire.controller;

import ir.malakouti.questionaire.controller.api.core.ResponseResult;
import ir.malakouti.questionaire.controller.api.core.ServiceResult;
import ir.malakouti.questionaire.convertor.AnswerConvertor;
import ir.malakouti.questionaire.model.dto.*;
import ir.malakouti.questionaire.model.entity.AnswerEntity;
import ir.malakouti.questionaire.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/answers")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;
    private final AnswerConvertor answerConvertor;

    @PostMapping("/save")
    public ResponseEntity<ServiceResult<AnswerOutputDto>> saveAnswer
            (@RequestBody AnswerRequestDto answerRequestDto) {
        AnswerDto result = answerService.saveAnswer(answerRequestDto);
        AnswerOutputDto answerOutputDto = answerConvertor.inputDtoToOutputDto(result);
        return ResponseEntity.ok(ServiceResult.success(answerOutputDto));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ServiceResult<AnswerOutputDto>> editAnswer
            (@PathVariable("id") Integer id, @RequestBody AnswerDto answerDto) {
        AnswerDto result = answerService.updateAnswer(id, answerDto);
        AnswerOutputDto answerOutputDto = answerConvertor.inputDtoToOutputDto(result);
        return ResponseEntity.ok(ServiceResult.success(answerOutputDto));

    }


    @GetMapping("/answer/{id}")
    public ResponseEntity<ServiceResult<AnswerOutputDto>> getAnswerById(@PathVariable("id") Integer id) {
        AnswerDto result = answerService.findAnswerById(id);
        AnswerOutputDto answerOutputDto = answerConvertor.inputDtoToOutputDto(result);
        return ResponseEntity.ok(ServiceResult.success(answerOutputDto));
    }


    @GetMapping
    public ResponseEntity<ServiceResult<AnswerOutputDto>> getAllAnswers() {
        List<AnswerOutputDto> answerOutputDtos = new ArrayList<>();
        for (AnswerEntity answerEntity : answerService.loadAll()) {
            AnswerOutputDto answerOutputDto =
                    answerConvertor.inputDtoToOutputDto(answerConvertor.entityToDto(answerEntity));
            answerOutputDtos.add(answerOutputDto);
        }
        return ResponseEntity.ok(ServiceResult.success(answerOutputDtos));
    }


}
