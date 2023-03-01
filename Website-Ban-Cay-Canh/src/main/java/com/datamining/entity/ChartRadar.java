package com.datamining.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ChartRadar implements Serializable {
    @Id
    String name_cate;
    Double value_radar;
    long count_radar;
}
