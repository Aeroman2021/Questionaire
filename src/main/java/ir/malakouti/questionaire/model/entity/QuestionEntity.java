package ir.malakouti.questionaire.model.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "question")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;


    @Column(nullable = false)
    private String description;

}
