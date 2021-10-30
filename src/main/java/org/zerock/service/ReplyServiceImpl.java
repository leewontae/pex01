package org.zerock.service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mapper.BoardMapper;
import org.zerock.mapper.ReplyMapper;
import org.zerock.vo.Criteria;
import org.zerock.vo.ReplyPageDTO;
import org.zerock.vo.ReplyVO;

import java.util.List;

@Service
@Log4j
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    @Setter(onMethod_={@Autowired})
    private ReplyMapper mapper ;
    // mapper 가 하나면 자동 주입을 이용 했지만 두개 이상 부터는 @Setter을 이용해서 주입이 이루어진다.
    @Setter(onMethod_=@Autowired)
    private BoardMapper boardMapper;

    @Transactional
    @Override
    public int register(ReplyVO vo) {

        log.info("------register"+vo);
        boardMapper.updateReplyCnt(vo.getBno(),1);
        return mapper.insert(vo);
    }

    @Override
    public ReplyVO get(Long rno) {

        log.info("-------get"+rno);
        return mapper.read(rno) ;
    }

    @Override
    public int modify(ReplyVO vo) {

        log.info("-------modify"+vo);
        return mapper.update(vo);
    }

    @Transactional
    @Override
    public int remove(int rno) {

        log.info("=========remove"+ rno);

        Long xLong=Long.valueOf(rno);

        ReplyVO vo = mapper.read(xLong);
        boardMapper.updateReplyCnt(vo.getBno(),-1);
        return mapper.delete(rno);
    }

    @Override
    public List<ReplyVO> getList(Criteria cri, Long bno) {

        log.info("get reply list of a board " + bno);
        return mapper.getListWithPaging(cri,bno);
    }

    @Override
    public ReplyPageDTO getListPage(Criteria cri, Long bno) {


        return new ReplyPageDTO(
                mapper.getCountByBno(bno),
                mapper.getListWithPaging(cri,bno));

    }
}
