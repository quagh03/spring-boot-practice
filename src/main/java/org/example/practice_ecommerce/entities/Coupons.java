package org.example.practice_ecommerce.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "coupons")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Coupons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "is_active")
    private Boolean isActive;
}
