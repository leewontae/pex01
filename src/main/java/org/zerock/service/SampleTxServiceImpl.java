package org.zerock.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mapper.Sample1Mapper;
import org.zerock.mapper.Sample2Mapper;

@Service
@Log4j
public class SampleTxServiceImpl implements SampleTxService{

    @Setter(onMethod_={@Autowired})
    private Sample1Mapper mapper1;

    @Setter(onMethod_={@Autowired})
    private Sample2Mapper mapper2;

    @Transactional
    // 아이콘을 통해 크랜잭션 처리가 된 메서드로 구분해 준다. 이 어노테이션 사용씨 트랜잭션 적용되어
    //sample1 테이블에 저장되지만 sample2에 저장이안되어 트랜잭션에 의해 sample1도 저장취소 된다.
    @Override
    public void addData(String value) {

        log.info("mapper1........");
        mapper1.insertCol1(value);

        log.info("mapper2........");
        mapper2.insertCol2(value);

        log.info("end.........");
    }
}
