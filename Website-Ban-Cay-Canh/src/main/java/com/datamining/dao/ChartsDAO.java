package com.datamining.dao;

import com.datamining.entity.ChartRadar;
import com.datamining.entity.Charts;
import com.datamining.entity.Years;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChartsDAO extends JpaRepository<Charts, Integer> {
    @Query("Select new Years(year(update_date)) from Order group by year(update_date)")
    List<Years> getYear();

    @Query("SELECT new Charts(month(c.update_date), sum(c.total) ) FROM Order c where YEAR(c.update_date) = ?1 GROUP BY month(c.update_date)")
    List<Charts> getValue(Integer year);


}
