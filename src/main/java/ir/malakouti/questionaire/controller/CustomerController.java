package ir.malakouti.questionaire.controller;

import ir.malakouti.questionaire.convertor.CustomerConvertor;
import ir.malakouti.questionaire.model.dto.*;
import ir.malakouti.questionaire.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerConvertor customerConvertor;

    @RequestMapping("/save")
    public ResponseEntity<ResponseResult<CustomerOutputDto>> saveAnswer
            (@RequestBody CustomerRequestDto customerRequestDto){
        CustomerDto result = customerService.registerCustomer(customerRequestDto);
        CustomerOutputDto customerOutputDto = customerConvertor.inputDtoToOutputDto(result);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseResult.<CustomerOutputDto>builder()
                        .code(0)
                        .data(customerOutputDto)
                        .message("Customer registered successfully...")
                        .build());
    }
}
