package ir.malakouti.questionaire.service.impl;

import ir.malakouti.questionaire.convertor.AnswersConvertor;
import ir.malakouti.questionaire.convertor.CustomersConvertor;
import ir.malakouti.questionaire.convertor.QuestionsConvertor;
import ir.malakouti.questionaire.exception.AnswerException;
import ir.malakouti.questionaire.exception.CustomerException;
import ir.malakouti.questionaire.model.dto.*;
import ir.malakouti.questionaire.model.entity.AnswerEntity;
import ir.malakouti.questionaire.model.entity.CustomerEntity;
import ir.malakouti.questionaire.model.entity.QuestionEntity;
import ir.malakouti.questionaire.repo.AnswerRepository;
import ir.malakouti.questionaire.service.AnswerService;
import ir.malakouti.questionaire.service.core.FilterData;
import ir.malakouti.questionaire.service.core.FilterSetter;
import ir.malakouti.questionaire.utils.filter.FilterWrapper;
import ir.malakouti.questionaire.utils.filter.SortWrapper;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@
public class AnswerServiceImpl extends FilterSetter<AnswerEntity> implements AnswerService {

    private  AnswerRepository answerRepository;
    private  CustomerServiceImpl customerService;
    private  QuestionServiceImpl questionService;
    private  AnswersConvertor answersConvertor;
    private  CustomersConvertor customersConvertor;
    private  QuestionsConvertor questionsConvertor;

    @PersistenceContext
    EntityManager entityManager;

    public AnswerServiceImpl(FilterData<AnswerEntity> filterData) {
        super(filterData);
    }



    @PostConstruct
    void init(){


    }


    @Override
    public AnswerDto save(AnswerRequestDto answerRequestDto) {
        try {
            AnswerEntity answerEntity = new AnswerEntity();
            if (answerRequestDto.getCustomerId() != null) {
                CustomerDto customerDto = customerService.findById(answerRequestDto.getCustomerId());
                CustomerEntity customerEntity = customersConvertor.dtoToEntity(customerDto);
                answerEntity.setCustomerId(customerEntity);
            }
            if (answerRequestDto.getQuestionId() != null) {
                QuestionDto questionDto = questionService.findById(answerRequestDto.getQuestionId());
                QuestionEntity questionEntity = questionsConvertor.dtoToEntity(questionDto);
                answerEntity.setQuestionId(questionEntity);
            }
            AnswerEntity result = answerRepository.save(answerEntity);
            return answersConvertor.entityToDto(result);

        } catch (CustomerException e) {
            throw new AnswerException("Unable to save answer");
        }

    }

    @Override
    public AnswerDto update(Integer id, AnswerDto answerDto) {
        try {
            AnswerEntity answer = answerRepository.findById(id).get();
            AnswerEntity answerEntity = new AnswerEntity();
            if (answerDto.getCustomerId() != null) {
                CustomerDto customerDto = customerService.findById(answerDto.getCustomerId());
                CustomerEntity customerEntity = customersConvertor.dtoToEntity(customerDto);
                answerEntity.setCustomerId(customerEntity);
            }
            if (answerDto.getQuestionId() != null) {
                QuestionDto questionDto = questionService.findById(answerDto.getQuestionId());
                QuestionEntity questionEntity = questionsConvertor.dtoToEntity(questionDto);
                answerEntity.setQuestionId(questionEntity);
            }
            AnswerEntity result = answerRepository.save(answerEntity);
            return answersConvertor.entityToDto(result);

        } catch (CustomerException e) {
            throw new AnswerException("Unable to update answer");
        }
    }

    @Override
    public void DeleteById(Integer id) {
        answerRepository.deleteById(id);
    }

    @Override
    public AnswerDto findById(Integer id) {
        try {
            return answersConvertor.entityToDto(answerRepository.findById(id).get());
        } catch (CustomerException e) {
            throw new CustomerException();
        }
    }

    @Override
    public List<AnswerDto> findAll() {
        List<AnswerDto> answerDtos = new ArrayList<>();
        for (AnswerEntity tempAnswer : answerRepository.findAll()) {
            AnswerDto answerDto = answersConvertor.entityToDto(tempAnswer);
            answerDtos.add(answerDto);
        }
        return answerDtos;
    }

    @Override
    public Map<String, Object> findAnswersByFilter(FilterWrapper filterWrapper, SortWrapper sortWrapper, Integer start, Integer end) {
        return null;
    }



}
