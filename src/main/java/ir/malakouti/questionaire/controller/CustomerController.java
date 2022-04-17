package ir.malakouti.questionaire.controller;

import ir.malakouti.questionaire.convertor.CustomerConvertor;
import ir.malakouti.questionaire.model.dto.*;
import ir.malakouti.questionaire.model.entity.CustomerEntity;
import ir.malakouti.questionaire.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerConvertor customerConvertor;

    @PostMapping("/save")
    public ResponseEntity<ResponseResult<CustomerOutputDto>> saveCustomer
            (@RequestBody CustomerRequestDto customerRequestDto) {
        CustomerDto result = customerService.registerCustomer(customerRequestDto);
        CustomerOutputDto customerOutputDto = customerConvertor.inputDtoToOutputDto(result);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseResult.<CustomerOutputDto>builder()
                        .code(0)
                        .data(customerOutputDto)
                        .message("Customer registered successfully...")
                        .build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseResult<CustomerOutputDto>> editCustomer
            (@PathVariable("id") Integer id, @RequestBody CustomerDto customerDto) {
        CustomerDto result = customerService.editCustomer(id, customerDto);
        CustomerOutputDto customerOutputDto = customerConvertor.inputDtoToOutputDto(result);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseResult.<CustomerOutputDto>builder()
                        .code(0)
                        .data(customerOutputDto)
                        .message("Customer registered successfully...")
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseResult<CustomerOutputDto>> getCustomerById(@PathVariable("id") Integer id) {
        CustomerEntity result = customerService.loadById(id);
        CustomerOutputDto customerOutputDto =
                customerConvertor.inputDtoToOutputDto(customerConvertor.entityToDto(result));

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseResult.<CustomerOutputDto>builder()
                        .code(0)
                        .data(customerOutputDto)
                        .message("Customer list loaded successfully...")
                        .build());
    }


    @GetMapping
    public ResponseEntity<ResponseResult<CustomerOutputDto>> getAllCustomers() {
        List<CustomerOutputDto> customerOutputDtos = new ArrayList<>();
        List<CustomerDto> result = customerService.getAllCustomers();
        for (CustomerDto customerDto : result) {
            CustomerOutputDto customerOutputDto = customerConvertor.inputDtoToOutputDto(customerDto);
            customerOutputDtos.add(customerOutputDto);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseResult.<CustomerOutputDto>builder()
                        .code(0)
                        .dataList(customerOutputDtos)
                        .message("Customer list loaded successfully...")
                        .build());
    }
}