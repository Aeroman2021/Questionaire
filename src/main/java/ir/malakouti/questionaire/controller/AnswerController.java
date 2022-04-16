package ir.malakouti.questionaire.controller;

import ir.malakouti.questionaire.convertor.AnswerConvertor;
import ir.malakouti.questionaire.model.dto.AnswerDto;
import ir.malakouti.questionaire.model.dto.AnswerOutputDto;
import ir.malakouti.questionaire.model.dto.AnswerRequestDto;
import ir.malakouti.questionaire.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answers")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;
    private final AnswerConvertor answerConvertor;

    @RequestMapping("/save")
    public ResponseEntity<ResponseResult<AnswerOutputDto>> saveAnswer
            (@RequestBody AnswerRequestDto answerRequestDto){
        AnswerDto result = answerService.saveAnswer(answerRequestDto);
        AnswerOutputDto answerOutputDto = answerConvertor.inputDtoToOutputDto(result);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseResult.<AnswerOutputDto>builder()
                        .code(0)
                        .data(answerOutputDto)
                        .message("Answer added successfully...")
                        .build());
    }


}
