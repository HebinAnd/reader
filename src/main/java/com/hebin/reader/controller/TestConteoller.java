package com.hebin.reader.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TestConteoller {
    @PostMapping("/t/test")
    @ResponseBody
    public Map test(String contest){
        Map result = new HashMap<String, String>();
        result.put("test","这是一个测试"+contest);
        return result;
    }
}
