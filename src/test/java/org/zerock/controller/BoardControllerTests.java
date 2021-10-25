package org.zerock.controller;


import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({
        "file:webapp/WEB-INF/applicationContext.xml",
        "file:webapp/WEB-INF/dispatcher-servlet.xml"
})
@Log4j
public class BoardControllerTests {

    @Setter(onMethod_={@Autowired})
    private WebApplicationContext ctx;
    private MockMvc mockmvc ;

    //before가 적용된 메서드는 모든 테스츠 전에 매번 실행되는 메서드이다.
    @Before
    public void setup(){
        this.mockmvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void testlist() throws Exception{
        log.info(
                mockmvc.perform(MockMvcRequestBuilders.get("/board/list")).andReturn().getModelAndView().getModelMap());
//MockMvcRequestBuilders라는 존재를 이용하여 get 방식으로 호출한다.
//이후에는 BoardController의 getlist()에서 반환된 결과를 이용해서 model에 어떤 데이터들이 담겨 있는지 확인 한다.

    }
    @Test
    public void testListPaging()throws Exception{
        log.info(mockmvc.perform(
                MockMvcRequestBuilders.get("/board/list")
                        .param("pageNum","2")
                        .param("amount","50"))
                .andReturn().getModelAndView().getModelMap());
    }
    @Test
    public void testRegister() throws Exception{
        String resultPage = mockmvc.perform(MockMvcRequestBuilders.post("/board/register")
                .param("title","테스트 새글 제목 ")
                .param("content","테스트 새글 내용")
                .param("writer","user00")).andReturn().getModelAndView().getViewName();

        log.info("##########"+resultPage); // url 정보 나온다.
    }

    @Test
    public void testGet() throws  Exception{

        log.info(mockmvc.perform(MockMvcRequestBuilders.get("/board/get").param("bno","26"))
                .andReturn().getModelAndView().getModelMap());
    }
    @Test
    public void testRemove() throws Exception{
        //삭제전 데이터 베이스에 게시물 번호 확인 할것
            String resultPage = mockmvc.perform(MockMvcRequestBuilders.post("/board/remove").param("bno","25")).andReturn()
                    .getModelAndView().getViewName();

            log.info("ssssssssssss"+resultPage);
    }

    @Test
    public void testModify() throws Exception{
        String resultPage = mockmvc
                .perform(MockMvcRequestBuilders.post("/board/modify").param("bno","26")
                        .param("title","수정된 테스트 새글 확인")
                        .param("content","수정된 테스트 새들 내용")
                        .param("writer","user00"))
                .andReturn().getModelAndView().getViewName();
        log.info(resultPage);
    }
}
