package ir.malakouti.questionaire.controller;

import ir.malakouti.questionaire.controller.api.core.ResponseResult;
import ir.malakouti.questionaire.controller.api.core.ServiceResult;
import ir.malakouti.questionaire.convertor.CustomerConvertor;
import ir.malakouti.questionaire.model.dto.CustomerDto;
import ir.malakouti.questionaire.model.dto.CustomerOutputDto;
import ir.malakouti.questionaire.model.dto.CustomerRequestDto;
import ir.malakouti.questionaire.service.CustomerService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<ServiceResult<CustomerOutputDto>> saveCustomer
            (@RequestBody CustomerRequestDto customerRequestDto) {
        CustomerDto result = customerService.registerCustomer(customerRequestDto);
        CustomerOutputDto customerOutputDto = customerConvertor.inputDtoToOutputDto(result);
        return ResponseEntity.ok(ServiceResult.success(customerOutputDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ServiceResult<CustomerOutputDto>> editCustomer
            (@PathVariable("id") Integer id, @RequestBody CustomerDto customerDto) {
        CustomerDto result = customerService.editCustomer(id, customerDto);
        CustomerOutputDto customerOutputDto = customerConvertor.inputDtoToOutputDto(result);
        return ResponseEntity.ok(ServiceResult.success(customerOutputDto));
    }

    @GetMapping("customer/{id}")
    public ResponseEntity<ServiceResult<CustomerOutputDto>> getCustomerById(@PathVariable("id") Integer id) {
        CustomerDto customerDto = customerService.getCustomerById(id);
        CustomerOutputDto customerOutputDto = customerConvertor.inputDtoToOutputDto(customerDto);
        return ResponseEntity.ok(ServiceResult.success(customerOutputDto));
    }


    @GetMapping
    public ResponseEntity<ServiceResult<CustomerOutputDto>> getAllCustomers() {
        List<CustomerOutputDto> customerOutputDtos = new ArrayList<>();
        List<CustomerDto> result = customerService.getAllCustomers();
        for (CustomerDto customerDto : result) {
            CustomerOutputDto customerOutputDto = customerConvertor.inputDtoToOutputDto(customerDto);
            customerOutputDtos.add(customerOutputDto);
        }
        return ResponseEntity.ok(ServiceResult.success(customerOutputDtos));
    }
}
