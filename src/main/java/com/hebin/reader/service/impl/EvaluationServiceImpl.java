package com.hebin.reader.service.impl;

import com.hebin.reader.mapper.EvaluationMapper;
import com.hebin.reader.service.EvaluationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class EvaluationServiceImpl implements EvaluationService {
    @Resource
    EvaluationMapper evaluationMapper;
    public List<Map> findBookById(Long bookid) {
        List<Map> maps = evaluationMapper.selectByBookId(bookid);
        return maps;
    }
}
