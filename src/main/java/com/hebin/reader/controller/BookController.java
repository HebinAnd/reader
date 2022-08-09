package com.hebin.reader.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hebin.reader.entity.Book;
import com.hebin.reader.service.BookService;
import com.hebin.reader.utils.ResponseUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Resource
    BookService bookService;

    @GetMapping("/list")
    public ResponseUtils findAll(Long categoryId,String order,Integer page){
        ResponseUtils resp = null;
        try {
            IPage<Book> p = bookService.selectPage(categoryId,order,page,10);
            resp = new ResponseUtils().put("page",p);
        } catch (Exception e) {
            e.printStackTrace();
            resp=new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
        }
        return resp;
    }
    @GetMapping("/id/{id}")
    public ResponseUtils findBookById(@PathVariable(value = "id") Long bookId){
        ResponseUtils resp = null;
        try {

            Book bookById = bookService.findBookById(bookId);
            resp = new ResponseUtils().put("book",bookById);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
        }
        return resp;
    }

}
