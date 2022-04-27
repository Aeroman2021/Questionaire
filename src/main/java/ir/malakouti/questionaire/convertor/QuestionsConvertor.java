package ir.malakouti.questionaire.convertor;


import ir.malakouti.questionaire.model.dto.QuestionDto;
import ir.malakouti.questionaire.model.entity.QuestionEntity;
import org.springframework.stereotype.Component;

@Component
public class QuestionsConvertor {

    public QuestionEntity dtoToEntity(QuestionDto questionDto) {
        QuestionEntity questionEntity = QuestionEntity.builder()
                .title(questionDto.getTitle())
                .description(questionDto.getDescription())
                .build();

        if (questionDto.getId() != null) {
            questionEntity.setId(questionDto.getId());
        }

        return questionEntity;
    }

    public QuestionDto entityToDto(QuestionEntity questionEntity){
        QuestionDto questionDto = QuestionDto.builder()
                .id(questionEntity.getId())
                .title(questionEntity.getTitle())
                .description(questionEntity.getDescription())
                .build();

        if (questionEntity.getId() != null) {
            questionDto.setId(questionDto.getId());
        }
        return questionDto;
    }



}
