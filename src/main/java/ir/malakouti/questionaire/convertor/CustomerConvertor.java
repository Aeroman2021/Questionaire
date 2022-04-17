package ir.malakouti.questionaire.convertor;

import ir.malakouti.questionaire.model.dto.CustomerDto;
import ir.malakouti.questionaire.model.dto.CustomerOutputDto;
import ir.malakouti.questionaire.model.entity.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerConvertor {

    public CustomerDto entityToDto(CustomerEntity customerEntity) {
        CustomerDto customerDto = CustomerDto.builder()
                .firstName(customerEntity.getFirstName())
                .lastName(customerEntity.getLastName())
                .password(customerEntity.getPassword())
                .username(customerEntity.getUsername())
                .build();

        if (customerEntity.getId() != null) {
            customerDto.setId(customerEntity.getId());
        }

        if (customerEntity.getPersonalCharacterNumber() != null) {
            customerDto.setPersonalCharacterNumber(customerEntity.getPersonalCharacterNumber());
        }

        return customerDto;
    }

    public CustomerEntity dtoToEntity(CustomerDto customerDto) {
        CustomerEntity customerEntity = CustomerEntity
                .builder()
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .username(customerDto.getUsername())
                .password(customerDto.getPassword())
                .build();

        if (customerDto.getId() != null)
            customerEntity.setPersonalCharacterNumber(customerDto.getPersonalCharacterNumber());

        if (customerDto.getPersonalCharacterNumber() != null)
            customerEntity.setPersonalCharacterNumber(customerDto.getPersonalCharacterNumber());

        return customerEntity;
    }

    public CustomerOutputDto inputDtoToOutputDto(CustomerDto customerDto){
        return CustomerOutputDto.builder()
                .id(customerDto.getId())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .build();
    }
}
