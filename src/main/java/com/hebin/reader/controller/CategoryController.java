package com.hebin.reader.controller;

import com.hebin.reader.entity.Category;
import com.hebin.reader.service.CategoryService;
import com.hebin.reader.utils.ResponseUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Resource
    CategoryService categoryService;

    @GetMapping("/list")
    public ResponseUtils findlist(){
        //code = 0
        ResponseUtils resp = null;
        try {
            List<Category> all = categoryService.findAll();

            resp = new ResponseUtils().put("list",all);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
        }
        return resp;
    }

}
