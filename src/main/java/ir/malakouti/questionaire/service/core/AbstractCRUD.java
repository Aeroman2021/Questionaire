package ir.malakouti.questionaire.service.core;

import ir.malakouti.questionaire.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RequiredArgsConstructor
public class AbstractCRUD<T, ID> {

    private JpaRepository<T, ID> jpaRepository;


    public void setJpaRepository(JpaRepository<T, ID> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Transactional
    public T save(T t) {
        T result = jpaRepository.save(t);
        return result;
    }

    @Transactional
    public T update(T entity) {
        return jpaRepository.save(entity);
    }


    public T loadById(ID id) {
        return  jpaRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Entity with id " + id + " not existed!")
        );
    }

    public List<T> loadAll() {
        return jpaRepository.findAll();
    }

    @Transactional
    public void deleteById(ID id) {
        jpaRepository.deleteById(id);
    }
}
