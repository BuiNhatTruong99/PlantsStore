package com.datamining.controller;

import com.datamining.dao.ChartRadarDAO;
import com.datamining.dao.ChartsDAO;
import com.datamining.entity.ChartRadar;
import com.datamining.entity.Charts;
import com.datamining.entity.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ChartController {
    @Autowired
    ChartsDAO chartsDAO;

    @Autowired
    ChartRadarDAO radarDAO;

    @RequestMapping("/admin/charts-chartjs")
    public String chartjs() {

        return "../static/admin/charts-chartjs";
    }

    @RequestMapping("/admin/statistical")
    public String statistical(Model model, @RequestParam("year") Integer year) {
        List<Charts> chart_data = chartsDAO.getValue(year);
        model.addAttribute("chart_data", chart_data);
        List<ChartRadar> radar = radarDAO.getCategories(year);
        model.addAttribute("radar", radar);
        return "../static/admin/charts-chartjs";
    }

    @ModelAttribute("years")
    public List<Years> getYear() {
        return chartsDAO.getYear();
    }
}
