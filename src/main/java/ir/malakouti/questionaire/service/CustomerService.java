package ir.malakouti.questionaire.service;

import ir.malakouti.questionaire.convertor.CustomerConvertor;
import ir.malakouti.questionaire.exception.CustomerException;
import ir.malakouti.questionaire.model.dto.CustomerDto;
import ir.malakouti.questionaire.model.dto.CustomerRequestDto;
import ir.malakouti.questionaire.model.entity.CustomerEntity;
import ir.malakouti.questionaire.repo.CustomerRepository;
import ir.malakouti.questionaire.service.core.AbstractCRUD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CustomerService extends AbstractCRUD<CustomerEntity, Integer> {

    private CustomerRepository customerRepository;

    final private CustomerConvertor customerConvertor;


    @PostConstruct
    public void init() {
        setJpaRepository(customerRepository);
    }

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
            throw new CustomerException();
        }

    }


}
