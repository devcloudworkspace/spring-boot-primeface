package com.example.demo.jsf.controller;

import com.example.demo.jsf.bean.MemberBean;
import com.example.demo.jsf.bean.MemberList;
import com.example.demo.jsf.service.CallService;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Data
@Scope("request")
@Component("helloController")
public class HelloController {

    Logger logger = LoggerFactory.getLogger(HelloController.class);

    private MemberBean memberBean;
    private String message;

    @Autowired
    private MemberList memberList;

    @Autowired
    private CallService callService;

    @PostConstruct
    public void init(){
        memberBean = MemberBean.builder().build();
    }

    public String index(){
        return "index?faces-redirect=true";
    }

    public String addMember(){
        String status = memberList.addMember(memberBean);
        logger.info("Status:{}",status);
        setMessage("Hi, "+memberBean.getName());
        String res = callService.callSayHello(memberBean.getName());
        logger.info("Response:{}",res);
        memberBean.setName("");
        return "index?faces-redirect=true";
    }
}
