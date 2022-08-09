package com.hebin.reader.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("book")
public class Book {
    @TableId
    private Long bookId;
    private String bookName;
    private String subTitle;
    private String author;
    private String cover;
    private String description;
    private Long categoryId;
    private float evaluationScore;
    private Integer evaluationQuantity;
}
