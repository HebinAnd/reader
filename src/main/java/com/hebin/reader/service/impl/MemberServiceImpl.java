package com.hebin.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hebin.reader.entity.Evaluation;
import com.hebin.reader.entity.Member;
import com.hebin.reader.entity.MemberReadState;
import com.hebin.reader.mapper.EvaluationMapper;
import com.hebin.reader.mapper.MemberMapper;
import com.hebin.reader.mapper.MemberReadStateMapper;
import com.hebin.reader.service.MemberService;
import com.hebin.reader.service.exception.MemberException;
import com.hebin.reader.utils.Md5Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class MemberServiceImpl implements MemberService {
    @Resource
    MemberMapper memberMapper;
    @Resource
    MemberReadStateMapper memberReadStateMapper;
    @Resource
    EvaluationMapper evaluationMapper;


    @Transactional(rollbackFor = Exception.class)
    public Member createMember(String username, String password, String nickname) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<Member>();
        queryWrapper.eq("username",username);
        List<Member> members = memberMapper.selectList(queryWrapper);
        if(members.size()>0){
            throw new MemberException("用户已存在");
        }
        Member member = new Member();
        member.setUsername(username);
        member.setNickname(nickname);
        int salt = new Random().nextInt(1000) + 1000;
        member.setSalt(salt);
        member.setPassword(Md5Utils.md5Digest(password,salt));
        member.setCreateTime(new Date());
        memberMapper.insert(member);
        return member;
    }

    public Member checkLogin(String username, String password) {
        QueryWrapper<Member> wrapper = new QueryWrapper<Member>();
        wrapper.eq("username",username);
        Member member = memberMapper.selectOne(wrapper);
        if(member == null){
            throw new MemberException("该用户不存在");
        }
        String digest = Md5Utils.md5Digest(password, member.getSalt());
        if(!digest.equals(member.getPassword())){
            throw new MemberException("密码错误");
        }

        return member;
    }

    public Member selectById(Long mid) {

        return memberMapper.selectById(mid);
    }

    public MemberReadState selectStates(Long bookId, Long memberId) {
        QueryWrapper<MemberReadState> queryWrapper = new QueryWrapper<MemberReadState>();
        queryWrapper.eq("book_id",bookId).eq("member_id",memberId);
        MemberReadState memberReadState = memberReadStateMapper.selectOne(queryWrapper);
        return memberReadState;
    }

    public MemberReadState updateReadState(Long bookId, Long memberId, Integer readState) {
        QueryWrapper<MemberReadState> queryWrapper = new QueryWrapper<MemberReadState>();
        queryWrapper.eq("book_id",bookId).eq("member_id",memberId);
        MemberReadState memberReadState = memberReadStateMapper.selectOne(queryWrapper);
        if(memberReadState != null){
            memberReadState.setReadState(readState);
            memberReadState.setCreateTime(new Date());
            memberReadStateMapper.updateById(memberReadState);
        }else {
            memberReadState = new MemberReadState();
            memberReadState.setCreateTime(new Date());
            memberReadState.setBookId(bookId);
            memberReadState.setMemberId(memberId);
            memberReadState.setReadState(readState);
            memberReadStateMapper.insert(memberReadState);
        }

        return memberReadState;
    }

    public Evaluation evaluation(Long memberId, Long bookId, Integer score, String content) {
        Evaluation evaluation = new Evaluation();
        evaluation.setBookId(bookId);
        evaluation.setMemberId(memberId);
        evaluation.setCreateTime(new Date());
        evaluation.setContent(content);
        evaluation.setScore(score);
        evaluation.setState("enable");
        evaluation.setEnjoy(0);
        evaluationMapper.insert(evaluation);
        return evaluation;
    }

    public Evaluation enjoy(Long evaluationId) {
        Evaluation evaluation =evaluationMapper.selectById(evaluationId);
        evaluation.setEnjoy(evaluation.getEnjoy() + 1);
        evaluationMapper.updateById(evaluation);
        return evaluation;//1556467214205440001
    }


}
