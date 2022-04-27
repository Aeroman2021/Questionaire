package ir.malakouti.questionaire.service;


import ir.malakouti.questionaire.model.dto.CustomerDto;
import ir.malakouti.questionaire.model.dto.CustomerRequestDto;
import ir.malakouti.questionaire.utils.filter.FilterWrapper;
import ir.malakouti.questionaire.utils.filter.SortWrapper;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    CustomerDto save(CustomerRequestDto customerRequestDto);
    CustomerDto update(Integer id,CustomerDto customerDto);
    void DeleteById(Integer id);
    CustomerDto findById(Integer id);
    List<CustomerDto> findAll();
    Map<String,Object> findCustomersByFilter(FilterWrapper filterWrapper, SortWrapper sortWrapper,
                                            Integer start, Integer end);
}
