package cz.rb.bccustomer.rest;

import cz.rb.bccustomer.domain.service.CustomerService;
import cz.rb.projectcommon.model.customer.CustomerListMessage;
import cz.rb.projectcommon.model.customer.CustomerMessage;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerRestService {

    private final CustomerService customerService;


    @GetMapping("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<CustomerMessage> getCustomerById(final @PathVariable("customerId") Long customerId) {
        log.info("CustomerRestService | getCustomerById001 | starting request with id: {}", customerId);
        var response = customerService.getCustomer(customerId);
        log.info("CustomerRestService | getCustomerById002 | request finished: {}", response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<CustomerListMessage> getCustomers() {
        log.info("CustomerRestService | getCustomers001 | starting request for all customers");
        var response = customerService.getCustomers();
        log.info("CustomerRestService | getCustomers002 | request finished: {}", response);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<CustomerMessage> addCustomer(final @RequestBody CustomerMessage customerMessage) {
        log.info("CustomerRestService | addCustomer001 | starting request with customer: {}", customerMessage);
        var response = customerService.addCustomer(customerMessage);
        log.info("CustomerRestService | addCustomer002 | request finished: {}", response);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<CustomerMessage> updateCustomer(final @PathVariable("customerId") Long customerId, final @RequestBody CustomerMessage customerMessage) {
        log.info("CustomerRestService | updateCustomer001 | starting request with customer: {}", customerMessage);
        var response = customerService.updateCustomer(customerId, customerMessage);
        log.info("CustomerRestService | updateCustomer002 | request finished: {}", response);
        return ResponseEntity.ok(response);
    }


}
