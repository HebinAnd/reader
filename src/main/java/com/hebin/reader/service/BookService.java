package com.hebin.reader.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hebin.reader.entity.Book;

import java.util.List;
import java.util.Map;

public interface BookService {
    IPage<Book> selectPage(Long categoryId, String order, Integer page, int i);
    Book findBookById(Long bookId);
    void updateScore();
    IPage<Map> selectBookMap(Integer page,Integer rows);
    Book createBook(Book book);
    Book updateBook(Book book);

    void deleteBookByBookId(Long bookId);
}
