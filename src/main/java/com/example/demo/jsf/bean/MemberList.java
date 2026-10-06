package com.example.demo.jsf.bean;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Scope("session")
@Component
@Data
public class MemberList {

    private List<MemberBean> members;

    @PostConstruct
    public void init(){
        members = new ArrayList<>();
    }

    public String addMember(MemberBean bean){
        members.add(MemberBean.builder().name(bean.getName()).build());
        return "Success";
    }
}
