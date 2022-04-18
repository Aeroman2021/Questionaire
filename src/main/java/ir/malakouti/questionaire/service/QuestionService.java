package ir.malakouti.questionaire.service;

import ir.malakouti.questionaire.convertor.QuestionConvertor;
import ir.malakouti.questionaire.exception.QuestionException;
import ir.malakouti.questionaire.model.dto.CustomerDto;
import ir.malakouti.questionaire.model.dto.QuestionDto;
import ir.malakouti.questionaire.model.dto.QuestionRequestDto;
import ir.malakouti.questionaire.model.entity.QuestionEntity;
import ir.malakouti.questionaire.repo.QuestionRepository;
import ir.malakouti.questionaire.service.core.AbstractCRUD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService extends AbstractCRUD<QuestionEntity, Integer> {

    private final QuestionRepository questionRepository;
    private final QuestionConvertor questionConvertor;

    @PostConstruct
    public void init() {
        setJpaRepository(questionRepository);
    }

    @Transactional
    public QuestionDto saveQuestion(QuestionRequestDto questionRequestDto) {
        try {
            QuestionEntity question = QuestionEntity.builder()
                    .title(questionRequestDto.getTitle())
                    .description(questionRequestDto.getDescription())
                    .build();
            QuestionEntity result = super.save(question);
            return questionConvertor.entityToDto(result);

        } catch (QuestionException e) {
            throw new QuestionException();
        }
    }

    @Transactional
    public QuestionDto updateQuestion(Integer questionId, QuestionDto questionDto) {

        try{
            QuestionEntity foundedQuestion = super.loadById(questionId);

            if (questionDto.getDescription() != null) {
                foundedQuestion.setDescription(questionDto.getDescription());
            }

            if (questionDto.getTitle() != null) {
                foundedQuestion.setTitle(questionDto.getTitle());
            }

            return questionConvertor.entityToDto(super.update(foundedQuestion));

        }catch (QuestionException e){
            throw new QuestionException();
        }
    }

    public QuestionDto getQuestionById(Integer questionId){
        QuestionEntity result = super.loadById(questionId);
        return questionConvertor.entityToDto(result);
    }


    public List<QuestionDto> getAllQuestions(){
        List<QuestionDto> questionDtos = new ArrayList<>();
        for (QuestionEntity questionEntity : questionRepository.findAll()) {
            QuestionDto questionDto = questionConvertor.entityToDto(questionEntity);
            questionDtos.add(questionDto);
        }
        return questionDtos;
    }




}
