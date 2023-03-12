package com.datamining.dao;

import com.datamining.entity.ChartRadar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChartRadarDAO extends JpaRepository<ChartRadar, String> {

    @Query("SELECT new ChartRadar(c.name, sum(o.total), count(p.id)) from Order o join OrderDetail d on o.id = d.order join Product p" +
            " on d.product = p.id join Category c on c.id = p.categories where YEAR(o.update_date) = ?1 GROUP BY c.name")
    List<ChartRadar> getCategories(Integer year);
}
