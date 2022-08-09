package com.hebin.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hebin.reader.entity.Book;
import com.hebin.reader.mapper.BookMapper;
import com.hebin.reader.mapper.EvaluationMapper;
import com.hebin.reader.mapper.MemberReadStateMapper;
import com.hebin.reader.service.BookService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {

    @Resource
    BookMapper bookMapper;
    @Resource
    EvaluationMapper evaluationMapper;
    @Resource
    MemberReadStateMapper memberReadStateMapper;

    public IPage<Book> selectPage(Long categoryId, String order, Integer page, int i) {

        IPage<Book> page1 = new Page(page, i);
        QueryWrapper<Book> wrapper = new QueryWrapper<Book>();
        if(categoryId != null && categoryId != -1){
            wrapper.eq("category_id", categoryId);
        }
        if(order != null){
            if(order.equals("quantity")){
                wrapper.orderByDesc("evaluation_quantity");
            }else if(order.equals("score")){
                wrapper.orderByDesc("evaluation_score");
            }
        }else{
            wrapper.orderByDesc("evaluation_quantity");
        }
        page1 = bookMapper.selectPage(page1, wrapper);
        return page1;
    }

    public Book findBookById(Long bookId) {
        Book o =  bookMapper.selectById(bookId);
        return o;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateScore() {
        bookMapper.updateScore();
    }

    public IPage<Map> selectBookMap(Integer page, Integer rows) {
        IPage p = new Page(page,rows);
        p=bookMapper.selectBookMap(p);
        return p;
    }

    @Transactional(rollbackFor = Exception.class)
    public Book createBook(Book book) {
        bookMapper.insert(book);
        return book;
    }

    @Transactional(rollbackFor = Exception.class)
    public Book updateBook(Book book) {

        bookMapper.updateById(book);
        return null;
    }

    public void deleteBookByBookId(Long bookId) {
        bookMapper.deleteById(bookId);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("book_id",bookId);
        evaluationMapper.delete(queryWrapper);
        memberReadStateMapper.delete(queryWrapper);
    }

}
