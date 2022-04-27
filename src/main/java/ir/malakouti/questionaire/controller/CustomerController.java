package ir.malakouti.questionaire.controller;

import ir.malakouti.questionaire.controller.api.core.ServiceResult;
import ir.malakouti.questionaire.convertor.CustomersConvertor;
import ir.malakouti.questionaire.model.dto.CustomerDto;
import ir.malakouti.questionaire.model.dto.CustomerRequestDto;
import ir.malakouti.questionaire.service.impl.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {


    private final CustomersConvertor customerConvertor;
    private final CustomerServiceImpl customerServiceImpl;

    @PostMapping("/save")
    public ResponseEntity<ServiceResult<CustomerDto>> saveCustomer
            (@RequestBody CustomerRequestDto customerRequestDto) {
        CustomerDto result = customerServiceImpl.save(customerRequestDto);
        return ResponseEntity.ok(ServiceResult.success(result));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ServiceResult<CustomerDto>> editCustomer
            (@PathVariable("id") Integer id, @RequestBody CustomerDto customerDto) {
        CustomerDto result = customerServiceImpl.update(id,customerDto);
        return ResponseEntity.ok(ServiceResult.success(result));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ServiceResult<CustomerDto>> deleteCustomer(@PathVariable("id") Integer id) {
        CustomerDto result = customerServiceImpl.findById(id);
        customerServiceImpl.DeleteById(id);
        return ResponseEntity.ok(ServiceResult.success(result));
    }

    @GetMapping("customer/{id}")
    public ResponseEntity<ServiceResult<CustomerDto>> getCustomerById(@PathVariable("id") Integer id) {
        CustomerDto result = customerServiceImpl.findById(id);
        return ResponseEntity.ok(ServiceResult.success(result));
    }


    @GetMapping
    public ResponseEntity<ServiceResult<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (CustomerDto tempCustomer : customerServiceImpl.findAll()) {
            customerDtos.add(tempCustomer);
        }
        return ResponseEntity.ok(ServiceResult.success(customerDtos));
    }
}
