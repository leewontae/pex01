package org.zerock.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration({"file:webapp/WEB-INF/applicationContext.xml"})

public class SampleTxServiceTests {

    @Setter(onMethod_={@Autowired})
    private SampleTxService service;

    @Test
    public void testLong(){

        String str ="Starry\r\n"+
                "Starry night\r\n"+
                "Paint your palette blue and grey\r\n"+
                "Look out on a summer's day";
        log.info("@@@@@@@@@@@@@@@@@"+str.getBytes().length);
        service.addData(str);
        //@Transactional 미적용시
        // 오류 뜬다. tbl_sample1에는 str이 저장되지만 tbl_sample2은 저장 길이가 짧아서 저장되지 않는다.

        //@Transactional 적용시
        // 아이콘을 통해 크랜잭션 처리가 된 메서드로 구분해 준다. 이 어노테이션 사용씨 트랜잭션 적용되어
        //sample1 테이블에 저장되지만 sample2에 저장이안되어 트랜잭션에 의해 sample1도 저장취소 된다.

        //
    }
}
