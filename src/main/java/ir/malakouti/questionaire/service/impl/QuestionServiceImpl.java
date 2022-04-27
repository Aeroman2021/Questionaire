package ir.malakouti.questionaire.service.impl;

import ir.malakouti.questionaire.convertor.QuestionsConvertor;
import ir.malakouti.questionaire.exception.CustomerException;
import ir.malakouti.questionaire.exception.QuestionException;
import ir.malakouti.questionaire.model.dto.QuestionDto;
import ir.malakouti.questionaire.model.dto.QuestionRequestDto;
import ir.malakouti.questionaire.model.entity.QuestionEntity;
import ir.malakouti.questionaire.repo.QuestionRepository;
import ir.malakouti.questionaire.service.QuestionService;
import ir.malakouti.questionaire.utils.filter.FilterWrapper;
import ir.malakouti.questionaire.utils.filter.SortWrapper;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionsConvertor convertor;


    @Override
    public QuestionDto save(QuestionRequestDto questionRequestDto) {
        try {
            QuestionEntity question = QuestionEntity.builder()
                    .title(questionRequestDto.getTitle())
                    .description(questionRequestDto.getDescription())
                    .build();

            QuestionEntity result = questionRepository.save(question);
            return convertor.entityToDto(result);

        } catch (CustomerException e) {
            throw new QuestionException("Unable to save the question");
        }
    }

    @Override
    public QuestionDto update(Integer id, QuestionDto questionRequestDto) {
        try {
            QuestionEntity questionEntity = questionRepository.findById(id).get();

            if (questionEntity.getTitle() != null) {
                questionEntity.setTitle(questionEntity.getTitle());
            }

            if (questionEntity.getDescription() != null) {
                questionEntity.setDescription(questionEntity.getDescription());
            }

            QuestionEntity result = questionRepository.save(questionEntity);
            return convertor.entityToDto(result);
        } catch (CustomerException e) {
            throw new QuestionException("Unable to update the question");
        }
    }

    @Override
    public void deleteById(Integer id) {
        questionRepository.deleteById(id);
    }

    @Override
    public QuestionDto findById(Integer id) {
        try {
            return convertor.entityToDto(questionRepository.findById(id).get());
        } catch (CustomerException e) {
            throw new CustomerException();
        }
    }

    @Override
    public List<QuestionDto> findAll() {
        List<QuestionDto> questionDtos = new ArrayList<>();
        for (QuestionEntity tempQuestion : questionRepository.findAll()) {
            questionDtos.add(convertor.entityToDto(tempQuestion));
        }
        return questionDtos;
    }

    @Override
    public Map<String, Object> findQuestionsByFilter(FilterWrapper filterWrapper, SortWrapper sortWrapper, Integer start, Integer end) {
        return null;
    }
}
