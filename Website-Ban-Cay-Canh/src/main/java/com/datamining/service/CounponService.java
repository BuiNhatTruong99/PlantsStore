package com.datamining.service;

import com.datamining.entity.Coupon;

import java.util.List;

public interface CounponService {
    List<Coupon> findAll();

    Coupon create(Coupon coupon);

    void delete(Integer id);

    Coupon put(Coupon coupon);
    Coupon findbyCode(String code);
}
