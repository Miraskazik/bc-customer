package cz.rb.bccustomer.domain.exception;

import jakarta.ws.rs.NotFoundException;

public class CustomerNotFoundException extends NotFoundException {

    public CustomerNotFoundException(final String message) {
        super(message);
    }
}
