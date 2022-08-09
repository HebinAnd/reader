package com.hebin.reader.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("member")
public class Member {
    @TableId(type = IdType.AUTO)
    private Long memberId;
    private String username;
    private String password;
    private Integer salt;
    private String nickname;
    private Date createTime;
}
