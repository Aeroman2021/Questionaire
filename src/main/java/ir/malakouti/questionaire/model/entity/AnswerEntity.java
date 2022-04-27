package ir.malakouti.questionaire.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties(value = "customer")
@Table(name = "answers")
public class AnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(orphanRemoval = true)
    private QuestionEntity questionId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerId;

    @Column(nullable = false)
    private Integer rate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerEntity that = (AnswerEntity) o;
        return questionId.equals(that.questionId) &&
                customerId.equals(that.customerId) &&
                rate.equals(that.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, customerId, rate);
    }


}
