package org.zerock.service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.mapper.BoardMapper;
import org.zerock.vo.BoardVo;
import org.zerock.vo.Criteria;

import java.util.List;
@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{

    @Setter(onMethod_=@Autowired)
    private BoardMapper mapper;

    @Override
    public void add(BoardVo boardVo) {

        log.info("register....."+ boardVo);
        mapper.insertSelectKey(boardVo);
    }

    @Override
    public BoardVo get(long bno) {

        return mapper.read(bno);

    }

    @Override
    public boolean modify(BoardVo boardVo) {


       return mapper.modify(boardVo) ==1 ;
    }

    @Override
    public boolean remove(long bno) {

        return mapper.delete(bno) ==1;
    }

    @Override
    public List<BoardVo> getList() {

        log.info("List .......");


        return mapper.getList();
    }

    @Override
    public List<BoardVo> getList(Criteria criteria) {

        log.info("List ......." +criteria);


        return mapper.getListWithPaging(criteria);
    }

    @Override
    public int getTotal(Criteria cri){
        log.info("get total count");
        return mapper.getTotalCount(cri);
    }
}
