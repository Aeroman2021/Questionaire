package ir.malakouti.questionaire.controller;

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
    public ResponseEntity<ResponseResult<AnswerOutputDto>> saveAnswer
            (@RequestBody AnswerRequestDto answerRequestDto) {
        AnswerDto result = answerService.saveAnswer(answerRequestDto);
        AnswerOutputDto answerOutputDto = answerConvertor.inputDtoToOutputDto(result);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseResult.<AnswerOutputDto>builder()
                        .code(0)
                        .data(answerOutputDto)
                        .message("Answer added successfully...")
                        .build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseResult<AnswerOutputDto>> editAnswer
            (@PathVariable("id") Integer id, @RequestBody AnswerDto answerDto) {
        AnswerDto result = answerService.updateAnswer(id, answerDto);
        AnswerOutputDto answerOutputDto = answerConvertor.inputDtoToOutputDto(result);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseResult.<AnswerOutputDto>builder()
                        .code(0)
                        .data(answerOutputDto)
                        .message("Answer updated successfully...")
                        .build());
    }


    @GetMapping
    public ResponseEntity<ResponseResult<AnswerOutputDto>> getAnswerById(@PathVariable("id") Integer id) {
        AnswerEntity result = answerService.loadById(id);
        AnswerOutputDto answerOutputDto =
                answerConvertor.inputDtoToOutputDto(answerConvertor.entityToDto(result));

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseResult.<AnswerOutputDto>builder()
                        .code(0)
                        .data(answerOutputDto)
                        .message("Answer list loaded successfully...")
                        .build());
    }


    @GetMapping
    public ResponseEntity<ResponseResult<AnswerOutputDto>> getAllAnswers() {
        List<AnswerOutputDto> answerOutputDtos = new ArrayList<>();
        for (AnswerEntity answerEntity : answerService.loadAll()) {
            AnswerOutputDto answerOutputDto =
                    answerConvertor.inputDtoToOutputDto(answerConvertor.entityToDto(answerEntity));
            answerOutputDtos.add(answerOutputDto);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseResult.<AnswerOutputDto>builder()
                        .code(0)
                        .dataList(answerOutputDtos)
                        .message("Answer list loaded successfully...")
                        .build());
    }


}
