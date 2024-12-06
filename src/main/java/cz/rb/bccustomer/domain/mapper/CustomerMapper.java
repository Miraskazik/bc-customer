package cz.rb.bccustomer.domain.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.rb.bccustomer.domain.repository.entity.CustomerEntity;
import cz.rb.projectcommon.model.customer.CustomerListMessage;
import cz.rb.projectcommon.model.customer.CustomerMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CustomerMapper {

    CustomerMessage toMessage(final CustomerEntity entity);

    List<CustomerMessage> toMessages(final List<CustomerEntity> entities);

    @Mapping(target = "dogsId", ignore = true)
    List<CustomerEntity> toEntities(final List<CustomerMessage> entities);

    @Mapping(target = "dogsId", ignore = true)
    CustomerEntity toEntity(final CustomerMessage message);


    default CustomerListMessage toListMessage(final List<CustomerEntity> entities) {
        return new CustomerListMessage(toMessages(entities));
    }

    default List<Long> parseDogsId(final String dogsId) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(dogsId, new TypeReference<List<Long>>() {
            });
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

}
