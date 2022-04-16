package ir.malakouti.questionaire.repo;

import ir.malakouti.questionaire.model.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionEntity,Integer> {
}
