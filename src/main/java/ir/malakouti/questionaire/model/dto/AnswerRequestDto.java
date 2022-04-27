package ir.malakouti.questionaire.model.dto;

import ir.malakouti.questionaire.model.entity.CustomerEntity;
import ir.malakouti.questionaire.model.entity.QuestionEntity;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AnswerRequestDto {
    private Integer questionId;
    private Integer customerId;
    private Integer rate;
}
