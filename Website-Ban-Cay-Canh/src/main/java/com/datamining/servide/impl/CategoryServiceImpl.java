package com.datamining.servide.impl;

import com.datamining.dao.CategoryDAO;
import com.datamining.entity.Category;
import com.datamining.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryDAO cdao;

    @Override
    public List<Category> findAll() {
        // TODO Auto-generated method stub
        return cdao.findAll();
    }

    @Override
    //save
    public Category save(Category category) {
        // TODO Auto-generated method stub
        return cdao.save(category);
    }

    @Override
    public String findIdByUrlEquals(String url) {
        // TODO Auto-generated method stub
        return cdao.findIdByUrlEquals(url);
    }

    @Override
    public Category findById(Integer id) {
        return cdao.findById(id).orElse(null);
    }


}
