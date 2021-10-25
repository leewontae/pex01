package org.zerock.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.vo.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:webapp/WEB-INF/applicationContext.xml")
@Log4j
public class BoardServiceTests {

    @Setter(onMethod_=@Autowired)
    private BoardService service;

    @Test
    public void testGetList(){
        service.getList(new Criteria(2,10)).forEach(boardVo -> log.info(boardVo));
    }
}
