package ir.malakouti.questionaire.model.dto;

import ir.malakouti.questionaire.model.entity.AnswerEntity;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CustomerRequestDto {
    private String firstName;
    private String lastName;
    private AnswerDto answer;
    private Long personalCharacterNumber;
    private String username;
    private String password;
}
