package ir.malakouti.questionaire.convertor;

import ir.malakouti.questionaire.model.dto.AnswerDto;
import ir.malakouti.questionaire.model.dto.AnswerOutputDto;
import ir.malakouti.questionaire.model.entity.AnswerEntity;
import org.springframework.stereotype.Component;

@Component
public class AnswerConvertor {

    public AnswerDto entityToDto(AnswerEntity answerEntity) {

        AnswerDto answerDto = AnswerDto.builder()
                .customer(answerEntity.getCustomer())
                .question(answerEntity.getQuestion())
                .rate(answerEntity.getRate()).build();

        if (answerEntity.getId() != null) {
            answerDto.setId(answerEntity.getId());
        }

        return answerDto;
    }

    public AnswerEntity dtoToEntity(AnswerDto answerDto) {
        AnswerEntity answerEntity = AnswerEntity.builder()
                .customer(answerDto.getCustomer())
                .question(answerDto.getQuestion())
                .rate(answerDto.getRate()).build();
        if (answerDto.getId() != null) {
            answerDto.setId(answerDto.getId());
        }

        return answerEntity;
    }

    public AnswerOutputDto inputDtoToOutputDto(AnswerDto answerDto) {
        return AnswerOutputDto.builder()
                .id(answerDto.getId())
                .customer(answerDto.getCustomer())
                .build();
    }


}
