package ir.malakouti.questionaire.service.impl;

import ir.malakouti.questionaire.convertor.CustomersConvertor;
import ir.malakouti.questionaire.exception.CustomerException;
import ir.malakouti.questionaire.model.dto.CustomerDto;
import ir.malakouti.questionaire.model.dto.CustomerRequestDto;
import ir.malakouti.questionaire.model.entity.CustomerEntity;
import ir.malakouti.questionaire.repo.CustomerRepository;
import ir.malakouti.questionaire.service.CustomerService;
import ir.malakouti.questionaire.utils.filter.FilterWrapper;
import ir.malakouti.questionaire.utils.filter.SortWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomersConvertor convertor;

    @Override
    @Transactional
    public CustomerDto save(CustomerRequestDto customerRequestDto) {
        try {
            CustomerEntity customer = CustomerEntity.builder()
                    .firstName(customerRequestDto.getFirstName())
                    .lastName(customerRequestDto.getLastName())
                    .username(customerRequestDto.getUsername())
                    .password(customerRequestDto.getPassword())
                    .build();

            CustomerEntity save = customerRepository.save(customer);
            return convertor.entityToDto(save);

        } catch (CustomerException e) {
            throw new CustomerException("Unable to register customer");
        }
    }

    @Override
    @Transactional
    public CustomerDto update(Integer customerId, CustomerDto customerDto) {
        try {
            CustomerEntity foundedEntity = customerRepository.findById(customerId).get();

            if (customerDto.getFirstName() != null) {
                foundedEntity.setFirstName(customerDto.getFirstName());
            }

            if (customerDto.getLastName() != null) {
                foundedEntity.setLastName(customerDto.getLastName());
            }

            if (customerDto.getUsername() != null) {
                foundedEntity.setUsername(customerDto.getUsername());
            }

            if (customerDto.getPassword() != null) {
                foundedEntity.setPassword(customerDto.getPassword());
            }

            CustomerEntity updatedEntity = customerRepository.save(foundedEntity);
            return convertor.entityToDto(updatedEntity);
        } catch (CustomerException e) {
            throw new CustomerException("Unable to update customer's information");
        }
    }

    @Override
    public void DeleteById(Integer id) {
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerDto findById(Integer id) {
        try {
            return convertor.entityToDto(customerRepository.findById(id).get());
        } catch (CustomerException e) {
            throw new CustomerException();
        }
    }

    @Override
    public List<CustomerDto> findAll() {
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (CustomerEntity customerEntity : customerRepository.findAll()) {
            customerDtos.add(convertor.entityToDto(customerEntity));
        }
        return customerDtos;
    }

    @Override
    public Map<String, Object> findCustomersByFilter(FilterWrapper filterWrapper, SortWrapper sortWrapper, Integer start, Integer end) {
        return null;
    }


}
