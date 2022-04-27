package ir.malakouti.questionaire.convertor;

import ir.malakouti.questionaire.model.dto.AnswerDto;
import ir.malakouti.questionaire.model.entity.AnswerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnswersConvertor {

    private final QuestionsConvertor questionsConvertor;
    private final CustomersConvertor customersConvertor;

    public AnswerDto entityToDto(AnswerEntity answerEntity) {

        AnswerDto answerDto = AnswerDto.builder()
                .customer(customersConvertor.entityToDto(answerEntity.getCustomer()))
                .question(questionsConvertor.entityToDto(answerEntity.getQuestion()))
                .rate(answerEntity.getRate()).build();

        if (answerEntity.getId() != null) {
            answerDto.setId(answerEntity.getId());
        }

        return answerDto;
    }

    public AnswerEntity dtoToEntity(AnswerDto answerDto) {
        AnswerEntity answerEntity = AnswerEntity.builder()
                .customer(customersConvertor.dtoToEntity(answerDto.getCustomer()))
                .question(questionsConvertor.dtoToEntity(answerDto.getQuestion()))
                .rate(answerDto.getRate()).build();
        if (answerDto.getId() != null) {
            answerDto.setId(answerDto.getId());
        }
        return answerEntity;
    }

}
