package cz.rb.bccustomer.domain.repository;

import cz.rb.bccustomer.domain.repository.entity.CustomerEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {


    @Modifying
    @Transactional
    @Query("UPDATE CustomerEntity c SET c.firstName = :firstName, c.lastName = :lastName, c.city = :city, " +
            "c.price = :price, c.details = :details, c.dogsId = :dogsId WHERE c.id = :id")
    void updateCustomer(
            @Param("id") Long id,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("city") String city,
            @Param("price") Integer price,
            @Param("details") String details,
            @Param("dogsId") String dogsId
    );
}
