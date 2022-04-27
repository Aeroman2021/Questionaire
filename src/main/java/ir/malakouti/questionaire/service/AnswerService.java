package ir.malakouti.questionaire.service;

import ir.malakouti.questionaire.model.dto.AnswerDto;
import ir.malakouti.questionaire.model.dto.AnswerRequestDto;
import ir.malakouti.questionaire.model.dto.CustomerDto;
import ir.malakouti.questionaire.model.dto.CustomerRequestDto;
import ir.malakouti.questionaire.utils.filter.FilterWrapper;
import ir.malakouti.questionaire.utils.filter.SortWrapper;

import java.util.List;
import java.util.Map;

public interface AnswerService {
    AnswerDto save(AnswerRequestDto customerRequestDto);
    AnswerDto update(Integer id, AnswerDto answerDto);
    void DeleteById(Integer id);
    AnswerDto findById(Integer id);
    List<AnswerDto> findAll();
    Map<String,Object> findAnswersByFilter(FilterWrapper filterWrapper, SortWrapper sortWrapper,
                                            Integer start, Integer end);
}
