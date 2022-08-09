package com.hebin.reader.controller;

import com.hebin.reader.entity.Evaluation;
import com.hebin.reader.entity.Member;
import com.hebin.reader.entity.MemberReadState;
import com.hebin.reader.service.MemberService;
import com.hebin.reader.utils.ResponseUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    @Resource
    MemberService memberService;

    @PostMapping("/registe")
    public ResponseUtils registe(String username, String password,String nickname, String vc , HttpServletRequest request){
        String verifyCode = (String) request.getSession().getAttribute("kaptchaVerifyCode");
        ResponseUtils resp;
        if(vc == null || verifyCode == null || !vc.equalsIgnoreCase(verifyCode)){
            resp = new ResponseUtils("VerifyCodeError","验证码错误");
        }else {
            try {
                memberService.createMember(username,password,nickname);
                resp = new ResponseUtils();
            } catch (Exception e) {
                e.printStackTrace();
                resp = new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
            }
            resp = new ResponseUtils();
        }
        return resp;
    }
    @PostMapping("/check_login")
    public ResponseUtils login(String username,String password,String vc,HttpServletRequest request){
        String verifyCode = (String)request.getSession().getAttribute("kaptchaVerifyCode");
        ResponseUtils resp;
        if(vc == null || verifyCode == null || !vc.equalsIgnoreCase(verifyCode)){
            resp = new ResponseUtils("VerifyCodeError","验证码错误");
        }else {
            resp = new ResponseUtils();
            try {
                Member member = memberService.checkLogin(username,password);
                member.setPassword(null);
                member.setSalt(null);
                resp = new ResponseUtils().put("member",member);
            } catch (Exception e) {
                e.printStackTrace();
                resp = new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
            }
        }
        return resp;
    }
    @GetMapping("/select_by_id")
    ///select_by_id?memberId="
    public ResponseUtils selectById(Long memberId){
        Member member = null;
        ResponseUtils resp = null;
        try {
            member = memberService.selectById(memberId);
            member.setSalt(null);
            member.setPassword(null);
            resp = new ResponseUtils().put("member",member);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
        }
        return resp;
    }
    @GetMapping("/select_read_state")
    public ResponseUtils selectReadState(Long bookId,Long memberId){

        ResponseUtils resp ;
        try {
            MemberReadState memberReadState = memberService.selectStates(bookId, memberId);
            resp = new ResponseUtils().put("readState",memberReadState);
        } catch (Exception e) {
            e.printStackTrace();
            resp=new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
        }
        return resp;
    }
    @PostMapping("update_read_state")
    public ResponseUtils updateReadState(Long bookId,Long memberId,Integer readState){
        ResponseUtils resp;
        try {
            MemberReadState memberReadState = memberService.updateReadState(bookId,memberId,readState);
            resp = new ResponseUtils().put("readState",memberReadState);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
        }
        return resp;
    }
    @PostMapping("/evaluate")
    @Transactional(rollbackFor = Exception.class)
    public ResponseUtils memberEvaluate(Long memberId,Long bookId,Integer score,String content){
        ResponseUtils resp ;
        try {
            Evaluation evaluation = memberService.evaluation(memberId,bookId,score,content);
            resp = new ResponseUtils().put("evaluate",evaluation);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
        }
        return resp;
    }
    @PostMapping("/enjoy")
    @Transactional(rollbackFor = Exception.class)
    public ResponseUtils enjoy(Long evaluationId){
        ResponseUtils resp ;
        try {
            Evaluation evaluation = memberService.enjoy(evaluationId);
            resp = new ResponseUtils().put("evaluate",evaluation);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
        }
        return resp;
    }
    //{"code":"0","message":"success","data":{"evaluate":{"evaluationId":1556479655832215554,

}
