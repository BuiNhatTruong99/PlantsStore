package com.datamining.controller;

import com.datamining.entity.Coupon;
import com.datamining.service.CounponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/coupon")
public class CouponController {
    @Autowired
    CounponService service;


    @GetMapping()
    public String getCouponBy(Model model, @RequestParam("code") String code) {

        Coupon cp = service.findbyCode(code);
    /*

            Integer value = cp.getValue
            Double couponPrice = total*value
            model.seta... ("couponprice", couponPrice)


            Double lastTotal = total - couponPrice
            model.seta... ("lastTotal", lastTotal)
     */

//    try {
//
//    }catch (Exception) {
//        thông báo
//    }



        model.addAttribute("coupon", cp);
//        System.out.println(cp);
        return "user/cart/cart-detail";
    }
}
