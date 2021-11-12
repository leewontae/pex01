package org.zerock.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j
@Controller
@RequestMapping(value = "/sample/*")
public class SampleController {

   /* @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
    @GetMapping("/annoMember")
    public void doMember2(){
        log.info("logined annotation member");
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/annoAdmin")
    public void doAdmin2(){
        log.info("admin annotataion only");
    }

    //시큐리티 어노테이션을 활설화 하기 위해서는 security-context가 아닌 dispatcher-servlet에서 작업 해야 한다.*/


    @GetMapping(value = "/all")
    public String doall(){

        log.info("do all can access everybody");
        return "all";
    }
    @GetMapping(value = "/member")
    public String domember(){

        log.info("do member can access everybody");
        return "member";
    }
    @GetMapping(value = "/admin")
    public String doadmin(){

        log.info("do admin can access everybody");
        return "admin";
    }
}
