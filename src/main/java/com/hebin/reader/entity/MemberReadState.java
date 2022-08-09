package com.hebin.reader.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("member_read_state")
@Data
public class MemberReadState {
    @TableId(type = IdType.AUTO)
    private Long rsId;

    private Long bookId;
    private Long memberId;
    private Integer readState;
    private Date createTime;
}
