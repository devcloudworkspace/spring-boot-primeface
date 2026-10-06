package com.example.demo.jsf.controller;

import com.example.demo.jsf.bean.MemberBean;
import com.example.demo.jsf.bean.MemberList;
import com.example.demo.jsf.bean.MemberListBean;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
@Data
@Scope("request")
@Component("memberViewController")
public class MemberViewController {

    private MemberListBean memberListBean;

    @Autowired
    private MemberList memberList;

    @PostConstruct
    public void init(){
        memberListBean = new MemberListBean();
        memberListBean.setMembers(new ArrayList<>());
    }

    public String showMembers(){
        getMemberListBean().getMembers().addAll(memberList.getMembers());
        return "members.faces";
    }
}
