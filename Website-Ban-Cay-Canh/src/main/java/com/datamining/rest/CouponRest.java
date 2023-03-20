package com.datamining.rest;

import com.datamining.entity.Coupon;
import com.datamining.service.CounponService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/coupon")
public class CouponRest {
    @Autowired
    CounponService service;

    @GetMapping
    public List<Coupon> getAll()
    {
        return service.findAll();
    }

    @PostMapping
    public Coupon create (@RequestBody Coupon coupon)
    {
        return service.create(coupon);
    }

    @PutMapping("{id}")
    public Coupon put(@PathVariable("id") Integer id,@RequestBody Coupon coupon) {
        return service.put(coupon);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id)
    {
        service.delete(id);
    }
}
