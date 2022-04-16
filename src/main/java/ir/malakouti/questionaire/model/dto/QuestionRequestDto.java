package ir.malakouti.questionaire.model.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class QuestionRequestDto {
    private String title;
    private String description;
}
