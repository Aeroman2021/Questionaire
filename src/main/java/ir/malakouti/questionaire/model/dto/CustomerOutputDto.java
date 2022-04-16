package ir.malakouti.questionaire.model.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CustomerOutputDto {
    private Integer id;
    private String firstName;
    private String lastName;
}
