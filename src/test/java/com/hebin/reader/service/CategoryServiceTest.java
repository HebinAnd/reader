package com.hebin.reader.service;

import com.hebin.reader.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class CategoryServiceTest {

    @Resource
    CategoryService categoryService;

    @Test
    public void findAll() {
        List<Category> all = categoryService.findAll();
        for (Category category : all) {
            System.out.println(category);
        }
    }
}