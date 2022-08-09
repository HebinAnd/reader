package com.hebin.reader.service;

import com.hebin.reader.entity.Member;
import com.hebin.reader.entity.MemberReadState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MemberServiceTest {

    @Resource
    MemberService memberService;


    @Test
    public void createMember() {
        Member member = memberService.createMember("binbin_1", "123456", "艾拉艾拉");
        System.out.println(member);
    }
    @Test
    public void createMember2() {
        Member member = memberService.createMember("binbin_1", "123456", "艾拉艾拉");
        System.out.println(member);
    }

    @Test
    public void checkLogin() {
        Member member = memberService.checkLogin("imooc_1", "123456");
        System.out.println(member);
    }
    @Test
    public void checkLogin2() {
        Member member = memberService.checkLogin("imooc_1x", "123456");
        System.out.println(member);
    }
    @Test
    public void checkLogin3() {
        Member member = memberService.checkLogin("imooc_1", "1234567");
        System.out.println(member);
    }

    @Test
    public void selectById() {
        Member member = memberService.selectById(1L);
        System.out.println(member);
    }

    @Test
    public void selectStates() {
        MemberReadState memberReadState = memberService.selectStates(5l, 1l);
        System.out.println(memberReadState);
    }
}