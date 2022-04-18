package ir.malakouti.questionaire.model.dto;

import lombok.*;

import javax.persistence.Column;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class QuestionOutputDto {
    private Integer id;
    private String title;
    private String description;
}
