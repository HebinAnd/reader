package com.hebin.reader.service;

import com.hebin.reader.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BookServiceTest {

    @Resource
    BookService bookService;
    @Test
    public void findBookById() {
        Book bookById = bookService.findBookById(5l);
        System.out.println(bookById);
    }
}