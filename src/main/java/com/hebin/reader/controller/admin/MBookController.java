package com.hebin.reader.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hebin.reader.entity.Book;
import com.hebin.reader.service.BookService;
import com.hebin.reader.utils.ResponseUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("api/management/book")
public class MBookController {
    @Resource
    BookService bookService;

    @GetMapping("/list")
    public ResponseUtils list(Integer page){
        if(page == null) page = 1;
        Integer rows = 10;
        ResponseUtils resp ;
        try {
            IPage<Map> p = bookService.selectBookMap(page, rows);
            resp = new ResponseUtils().put("list",p.getRecords()).put("count",p.getTotal());
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
        }
        return resp;
    }
    @PostMapping("/upload")
    public Map upload(@RequestParam("img") MultipartFile file, HttpServletRequest request) throws IOException {
        String uploadPath = request.getServletContext().getResource("/").getPath()+"/upload/";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String fileName = sdf.format(new Date());
        String substring = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        file.transferTo(new File(uploadPath+fileName+substring));
        Map result = new LinkedHashMap();
        result.put("errno",0);
        result.put("data", new String[]{"/upload/"+fileName+substring});
        return result;
    }

    @PostMapping("/create")
    public ResponseUtils createBook(Book book){
        System.out.println(book.getDescription());
        ResponseUtils resp ;
        try {
            Document doc = Jsoup.parse(book.getDescription());
            Elements img = doc.select("img");
            if (img.size()==0) {
                resp = new ResponseUtils("imageNotFoundError","图书描述中不含有封面");
                return resp;
            }
            String cover = img.first().attr("src");
            book.setCover(cover);
            book.setEvaluationScore(0f);
            book.setEvaluationQuantity(0);
            Book book1 = bookService.createBook(book);
            resp = new ResponseUtils().put("book",book1);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
        }
        return resp;
    }

    @PostMapping("/update")
    public ResponseUtils updateBook(Book book){
        ResponseUtils resp;

        try {
            Document doc = Jsoup.parse(book.getDescription());
            Elements img = doc.select("img");
            if (img.size()==0) {
                resp = new ResponseUtils("imageNotFoundError","图书描述中不含有封面");
                return resp;
            }
            Book b = bookService.findBookById(book.getBookId());
            b.setBookName(book.getBookName());
            b.setEvaluationQuantity(book.getEvaluationQuantity());
            b.setEvaluationScore(b.getEvaluationScore());
            b.setSubTitle(book.getSubTitle());
            b.setDescription(book.getDescription());
            b.setAuthor(book.getAuthor());
            b.setCover(book.getCover());
            b.setCategoryId(book.getCategoryId());
            Book book1 = bookService.updateBook(b);
            resp=new ResponseUtils().put("book",book1);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
        }
        return resp;
    }
    @PostMapping("delete")
    public ResponseUtils deleteBook(Long bookId){

        ResponseUtils resp ;
        try {
            bookService.deleteBookByBookId(bookId);
            resp = new ResponseUtils();
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
        }
        return resp;
    }
}
