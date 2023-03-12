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

    @Query("SELECT new Charts(month(c.update_date), sum(c.total), count(c.id)) FROM Order c where YEAR(c.update_date) = ?1 GROUP BY month(c.update_date)")
    List<Charts> getValue(Integer year);

    @Query("SELECT new Charts(month(o.update_date), sum(o.total), count(o.id)) FROM Order o WHERE YEAR(o.update_date) =?1 GROUP BY month(o.update_date)")
    List<Charts> getOrderStatistical(Integer year);
}
