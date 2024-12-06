package cz.rb.bccustomer.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.rb.bccustomer.domain.exception.CustomerNotFoundException;
import cz.rb.bccustomer.domain.mapper.CustomerMapper;
import cz.rb.bccustomer.domain.repository.CustomerRepository;
import cz.rb.projectcommon.model.customer.CustomerListMessage;
import cz.rb.projectcommon.model.customer.CustomerMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final ObjectMapper objectMapper;

    public CustomerMessage getCustomer(final Long id) {
        var customer = customerRepository.findById(id);

        return customer.map(customerMapper::toMessage).orElse(null);
    }

    public CustomerListMessage getCustomers() {
        var customer = customerRepository.findAll();

        return customerMapper.toListMessage(customer);
    }

    public CustomerMessage addCustomer(final CustomerMessage customer) {
        var customerEntity = customerMapper.toEntity(customer);
        customerEntity.setDogsId(parseDogsId(customer.getDogsId()));
        var savedCustomer = customerRepository.saveAndFlush(customerEntity);
        return customerMapper.toMessage(savedCustomer);
    }

    private String parseDogsId(final List<Long> dogsId) {
        try {
            return objectMapper.writeValueAsString(dogsId);
        } catch (final JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public CustomerMessage updateCustomer(final Long customerId, final CustomerMessage customer) {

        var actualCustomer = customerRepository.findById(customerId);

        if (actualCustomer.isEmpty()) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
        }

        customer.setId(actualCustomer.get().getId());
        updateCustomerInDatabase(customer, actualCustomer.get().getDogsId());

        return customer;
    }

    /**
     * Update zaznamu o zakaznikovi, ID a seznam psu zustava puvodni
     *
     * @param updatedCustomer updatovany zaznam
     * @param dogsId          puvodni senzam psu
     */
    private void updateCustomerInDatabase(final CustomerMessage updatedCustomer, final String dogsId) {
        customerRepository.updateCustomer(
                updatedCustomer.getId(), // id ponechame stejne
                updatedCustomer.getFirstName(),
                updatedCustomer.getLastName(),
                updatedCustomer.getCity(),
                updatedCustomer.getPrice(),
                updatedCustomer.getDetails(),
                dogsId);
    }
}
