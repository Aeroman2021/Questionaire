package ir.malakouti.questionaire.service;

import ir.malakouti.questionaire.convertor.AnswerConvertor;
import ir.malakouti.questionaire.exception.AnswerException;
import ir.malakouti.questionaire.model.dto.AnswerDto;
import ir.malakouti.questionaire.model.dto.AnswerRequestDto;
import ir.malakouti.questionaire.model.entity.AnswerEntity;
import ir.malakouti.questionaire.repo.AnswerRepository;
import ir.malakouti.questionaire.service.core.AbstractCRUD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class AnswerService extends AbstractCRUD<AnswerEntity, Integer> {

    private AnswerRepository answerRepository;
    private final AnswerConvertor answerConvertor;

    @PostConstruct
    public void init() {
        setJpaRepository(answerRepository);
    }

    @Transactional
    public AnswerDto saveAnswer(AnswerRequestDto answerRequestDto) {

        try {
            AnswerEntity answerEntity = AnswerEntity.builder()
                    .customer(answerRequestDto.getCustomer())
                    .question(answerRequestDto.getQuestion())
                    .rate(answerRequestDto.getRate())
                    .build();

            AnswerEntity result = answerRepository.save(answerEntity);
            return answerConvertor.entityToDto(result);
        } catch (AnswerException e) {
            throw new AnswerException("Unable to save Answer");
        }
    }

    @Transactional
    public AnswerDto updateAnswer(Integer questionId, AnswerDto answerDto) {

        try {
            AnswerEntity foundedEntity = super.loadById(questionId);

            foundedEntity.setCustomer(answerDto.getCustomer());
            foundedEntity.setQuestion(answerDto.getQuestion());
            foundedEntity.setRate(answerDto.getRate());

            AnswerEntity result = answerRepository.save(foundedEntity);
            return answerConvertor.entityToDto(result);
        } catch (AnswerException e) {
            throw new AnswerException("Unable to update Answer");
        }
    }




}
