package com.hebin.reader.service;

import java.util.List;
import java.util.Map;

public interface EvaluationService {
    List<Map> findBookById(Long bookid);
}
