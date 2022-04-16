package ir.malakouti.questionaire.repo;

import ir.malakouti.questionaire.model.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<AnswerEntity,Integer> {
}
