package ir.malakouti.questionaire.service;

import ir.malakouti.questionaire.model.dto.QuestionDto;
import ir.malakouti.questionaire.model.dto.QuestionRequestDto;
import ir.malakouti.questionaire.utils.filter.FilterWrapper;
import ir.malakouti.questionaire.utils.filter.SortWrapper;

import java.util.List;
import java.util.Map;

public interface QuestionService {
    QuestionDto save(QuestionRequestDto questionRequestDto);
    QuestionDto update(Integer id,QuestionDto questionDto);
    void deleteById(Integer id);
    QuestionDto findById(Integer id);
    List<QuestionDto> findAll();
    Map<String,Object> findQuestionsByFilter(FilterWrapper filterWrapper, SortWrapper sortWrapper,
                                            Integer start, Integer end);
}
