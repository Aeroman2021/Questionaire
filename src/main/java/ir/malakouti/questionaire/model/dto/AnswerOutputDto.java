package ir.malakouti.questionaire.model.dto;

import ir.malakouti.questionaire.model.entity.CustomerEntity;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AnswerOutputDto {
    private Integer id;
    private CustomerEntity customer;
}
