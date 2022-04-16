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
    private QuestionEntity question;
    private CustomerEntity customer;
    private Integer rate;
}
