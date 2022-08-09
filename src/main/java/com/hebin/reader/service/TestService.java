package com.hebin.reader.service;

import com.hebin.reader.mapper.TestMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class TestService {
    @Resource
    TestMapper testMapper;

    @Transactional
    public void batchInsert(){
        for (int i = 0; i < 5; i++) {
            /*if(i==2){
                throw new RuntimeException("未处理异常");
            }*/
            testMapper.insertSample();
        }
    }
}
