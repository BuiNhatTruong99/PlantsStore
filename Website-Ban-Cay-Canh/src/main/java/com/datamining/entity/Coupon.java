package com.datamining.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@Table(name = "Coupon")
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "code", nullable = false, length = 20)
    private String code;

    @Column(name = "value", nullable = false)
    private Integer value;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    @Column(name = "end_date", nullable = false)
    private Instant endDate;

}