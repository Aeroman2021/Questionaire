package ir.malakouti.questionaire.repo;

import ir.malakouti.questionaire.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Integer> {
}
