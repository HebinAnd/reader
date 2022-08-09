package com.hebin.reader.controller;

import com.hebin.reader.service.EvaluationService;
import com.hebin.reader.utils.ResponseUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/evaluation")
public class EvaluationController {

    @Resource
    EvaluationService evaluationService;

    @GetMapping("/list")
    ///api/evaluation/list
    public ResponseUtils list(Long bookId){
        ResponseUtils resp = null;
        try {
            List<Map> bookById = evaluationService.findBookById(bookId);
            resp = new ResponseUtils().put("list",bookById);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
        }
        return resp;
    }

}
