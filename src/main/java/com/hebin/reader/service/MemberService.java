package com.hebin.reader.service;

import com.hebin.reader.entity.Evaluation;
import com.hebin.reader.entity.Member;
import com.hebin.reader.entity.MemberReadState;

public interface MemberService {
    Member createMember(String username, String password, String nickname);

    Member checkLogin(String username, String password);

    Member selectById(Long mid);
    MemberReadState selectStates(Long bookId,Long memberId);

    MemberReadState updateReadState(Long bookId, Long memberId, Integer readState);

    Evaluation evaluation(Long memberId, Long bookId, Integer score, String content);

    Evaluation enjoy(Long evalutionId);
}
