package cz.rb.bccustomer.domain.repository.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "all_customers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "city", length = 50, nullable = false)
    private String city;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "details", nullable = false)
    private String details;

    @Column(name = "dogs_id", length = 100, nullable = false)
    private String dogsId;

}
