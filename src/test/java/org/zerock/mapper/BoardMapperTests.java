package org.zerock.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.service.BoardService;
import org.zerock.vo.BoardVo;
import org.zerock.vo.Criteria;

import java.util.List;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:webapp/WEB-INF/applicationContext.xml")
@Log4j
public class BoardMapperTests {

    @Setter(onMethod_=@Autowired)
    private BoardMapper mapper;

    @Setter(onMethod_=@Autowired )
    private BoardService service;
    @Test
    public void testGetList(){

        mapper.getList().forEach(boardVo -> log.info("asdasdass"+boardVo));
    }

    @Test
    public void testSearch(){
        Criteria cri = new Criteria();
        cri.setKeyword("새로");
        cri.setType("TC");

        List<BoardVo> list = mapper.getListWithPaging(cri);
        list.forEach(boardVo -> log.info(boardVo));
    }
    @Test
    public void testPaging(){
        Criteria cri = new Criteria();
      /*  List<BoardVo>list = mapper.getListWithPaging(cri);
        list.forEach(boardVo -> log.info(boardVo));*/

        cri.setPageNum(3);
        cri.setAmount(10);
        List<BoardVo>list = mapper.getListWithPaging(cri);
        list.forEach(boardVo -> log.info(boardVo.getBno()));
    }
    @Test
    public void inserttest(){

        BoardVo board = new BoardVo();
        board.setTitle("새로쓴 제목");
        board.setContent("새로작성하는 내용");
        board.setWriter("newbie");

        mapper.insert(board);

        log.info(board);
    }
    @Test
    public void testinsertselectkey(){

        BoardVo board = new BoardVo();
        board.setTitle("새로쓴 제목");
        board.setContent("새로작성하는 내용");
        board.setWriter("newbie");

        mapper.insertSelectKey(board);

        log.info(board);
    }

    @Test
    public void testRead(){

        BoardVo board = mapper.read(1);
        log.info(board);
    }

    @Test
    public void delete(){


        log.info(mapper.delete(1));

    }

    @Test
    public void update(){
        BoardVo boardVo= new BoardVo();
        boardVo.setBno(21);
        boardVo.setWriter("asdasd");
        boardVo.setTitle("asdasdw");
        boardVo.setContent("asdasdwww");

        log.info(mapper.modify(boardVo));

    }
    @Test
    public void textExist(){
        log.info(service);
        assertNotNull(service);
    }

    @Test
    public void testadd(){

        BoardVo boardVo = new BoardVo();
        boardVo.setTitle("새로 작성하는 글 ");
        boardVo.setContent("새로 작성하는 내용");
        boardVo.setWriter("newbie");
        service.add(boardVo);

        log.info("생성된 게시물 의 번호"+ boardVo.getBno());
    }

    @Test
    public void testGetlist(){

        service.getList().forEach(board->{log.info(board);});
    }

    @Test
    public void testGet(){

        service.get(24);
    }

    @Test
    public void testUpdate(){
        BoardVo board = service.get(21);

        if(board == null){

            return;

        }
        board.setTitle("제목 수정 합니데이!");
        log.info("modify result:" + service.modify(board));
    }


}
