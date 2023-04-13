package com.datamining.dao;

import com.datamining.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CouponDAO extends JpaRepository<Coupon, Integer> {
    @Query("SELECT c FROM Coupon c WHERE c.code = ?1")
    Coupon findByCode(String code);

}
