package com.datamining.servide.impl;

import com.datamining.dao.CouponDAO;
import com.datamining.entity.Coupon;
import com.datamining.service.CounponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CounponService {
    @Autowired
    CouponDAO cdao;

    @Override
    public List<Coupon> findAll() {
        return cdao.findAll();
    }

    @Override
    public Coupon create(Coupon coupon) {
        return cdao.save(coupon);
    }

    @Override
    public void delete(Integer id) {
        cdao.deleteById(id);
    }

    @Override
    public Coupon put(Coupon coupon) {
        return cdao.save(coupon);
    }

    @Override
    public Coupon findbyCode(String code) {
        return cdao.findByCode(code);
    }
}
