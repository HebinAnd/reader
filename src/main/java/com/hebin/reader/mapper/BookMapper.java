package com.hebin.reader.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hebin.reader.entity.Book;

import java.util.Map;


public interface BookMapper extends BaseMapper<Book> {
    public void updateScore();
    IPage<Map> selectBookMap(IPage page);
}
