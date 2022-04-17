package ir.malakouti.questionaire.service;

import ir.malakouti.questionaire.convertor.CustomerConvertor;
import ir.malakouti.questionaire.exception.CustomerException;
import ir.malakouti.questionaire.model.dto.CustomerDto;
import ir.malakouti.questionaire.model.dto.CustomerRequestDto;
import ir.malakouti.questionaire.model.dto.QuestionDto;
import ir.malakouti.questionaire.model.entity.CustomerEntity;
import ir.malakouti.questionaire.model.entity.QuestionEntity;
import ir.malakouti.questionaire.repo.CustomerRepository;
import ir.malakouti.questionaire.service.core.AbstractCRUD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService extends AbstractCRUD<CustomerEntity, Integer> {

    final private CustomerRepository customerRepository;
    final private CustomerConvertor customerConvertor;


    @PostConstruct
    public void init() {
        setJpaRepository(customerRepository);
    }

    @Transactional
    public CustomerDto registerCustomer(CustomerRequestDto customerRequestDto) {

        try {
            CustomerEntity customer = CustomerEntity.builder()
                    .firstName(customerRequestDto.getFirstName())
                    .lastName(customerRequestDto.getLastName())
                    .username(customerRequestDto.getUsername())
                    .password(customerRequestDto.getPassword())
                    .build();

            CustomerEntity savedEntity = super.save(customer);
            return customerConvertor.entityToDto(savedEntity);

        } catch (CustomerException e) {
            throw new CustomerException("Unable to register customer");
        }
    }

    @Transactional
    public CustomerDto editCustomer(Integer customerId, CustomerDto customerDto) {
        try {
            CustomerEntity foundedEntity = super.loadById(customerId);

            if (customerDto.getFirstName() != null) {
                foundedEntity.setFirstName(customerDto.getFirstName());
            }

            if (customerDto.getFirstName() != null) {
                foundedEntity.setFirstName(customerDto.getFirstName());
            }

            if (customerDto.getUsername() != null) {
                foundedEntity.setUsername(customerDto.getUsername());
            }

            if (customerDto.getPassword() != null) {
                foundedEntity.setPassword(customerDto.getPassword());
            }

            CustomerEntity updatedEntity = super.update(foundedEntity);
            return customerConvertor.entityToDto(updatedEntity);
        } catch (CustomerException e) {
            throw new CustomerException("Unable to update customer's information");
        }
    }

    public List<CustomerDto> getAllCustomers(){
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (CustomerEntity customerEntity : customerRepository.findAll()) {
            CustomerDto customerDto = customerConvertor.entityToDto(customerEntity);
            customerDtos.add(customerDto);
        }
        return customerDtos;
    }



}
