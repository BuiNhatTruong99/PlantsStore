package com.datamining.controller;

import com.datamining.entity.Coupon;
import com.datamining.service.CounponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Controller
@RequestMapping("/coupon")
public class CouponController {
    @Autowired
    CounponService service;

//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    Date datenow = new Date();
//    String date = sdf.format(datenow);

    @GetMapping()
    public String getCouponBy(Model model, @RequestParam("code") String code,HttpServletRequest request) {
        try {
            Coupon cp = service.findbyCode(code);
//            System.out.println(code);
//            System.out.println(code.equals(cp.getCode()));
            if(code.equals(cp.getCode()))
            {

                Instant Cdate = cp.getCreateDate();
                Instant Edate = cp.getEndDate();
                Instant CurrentDay = Instant.now();
//                System.out.println(CurrentDay);
//                System.out.println(Cdate.isAfter(CurrentDay));
//                System.out.println(Edate.isBefore(CurrentDay));
                if(Cdate.isBefore(CurrentDay) && Edate.isAfter(CurrentDay))
                {
                    model.addAttribute("message","Áp dụng thành công");

                    model.addAttribute("sale",cp.getValue());
                }else{
                    model.addAttribute("message","Mã hiện giờ chưa sử dụng được");
                    model.addAttribute("sale",0);
                }
            }else{
                model.addAttribute("message","Vui lòng kiểm tra lại mã");
                model.addAttribute("sale",0);
            }

        }catch(Exception e)
        {
            model.addAttribute("message","Áp dụng thất bại");
            model.addAttribute("sale",0);
        }

        return "user/cart/cart-detail";
    }
}
