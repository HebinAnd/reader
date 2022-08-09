package com.hebin.reader.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("evaluation")
@Data
public class Evaluation {
    @TableId
    private Long evaluationId;
    private String content;
    private int score;
    private Date createTime;
    private Long memberId;
    private Long bookId;
    private int enjoy;
    private String state;
    private String disableReason;
    private Date disableTime;
}
