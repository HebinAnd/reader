package com.hebin.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hebin.reader.entity.Category;
import com.hebin.reader.mapper.CategoryMapper;
import com.hebin.reader.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class CategoryServiceImpl implements CategoryService {
//extends ServiceImpl
    @Resource
    CategoryMapper categoryMapper;

    public List<Category> findAll() {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<Category>();
        queryWrapper.orderByAsc("category_id");
        return categoryMapper.selectList(queryWrapper);
    }
}
