package org.zerock.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.vo.Criteria;
import org.zerock.vo.ReplyVO;

import java.util.List;
import java.util.stream.IntStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:webapp/WEB-INF/applicationContext.xml")
@Log4j
public class ReplyMapperTests {
// board table에 있는 게시물 번호가 있느지 확인 해야 한다.
  private  Long[] bnoArr={24L,26L,41L,42L,43L};
  @Setter(onMethod_=@Autowired)
    private ReplyMapper mapper;
  @Test
  public void testCreate() {


    IntStream.rangeClosed(1, 10).forEach(i -> {
//IntStream과 LongStream 에는 range와 rangeClosed 메소드를 지원한다.
/* range(1,4)이면 123 이며 rangeclosed(1,5) 이면 12345 까지이다. */
      ReplyVO vo = new ReplyVO();

      vo.setBno(bnoArr[i % 5]);
      vo.setReply("댓글 테스트 " + i);
      vo.setReplyer("replyer" + i);

      mapper.insert(vo);

    });
  }
  @Test
  public void read(){

    long targetRno =5l;

    ReplyVO vo = mapper.read(targetRno);

    log.info(vo);
  }

  @Test
  public void testDelete(){
    Long targetRno =1l;
    int rno = Math.toIntExact(targetRno);
    mapper.delete(rno);
  }

    @Test
    public void testMapper(){

      log.info(mapper);
  }

  @Test
  public void testUpdate(){
    Long targetRno = 10l;
    ReplyVO vo = mapper.read(targetRno);
    vo.setReply("update reply");
    int count = mapper.update(vo);
    log.info("update count:"+count);
  }

  @Test
  public void testList(){
    Criteria cri = new Criteria();
    List<ReplyVO> replies = mapper.getListWithPaging(cri,bnoArr[0]);
    replies.forEach(reply-> log.info(reply));
  }

  // 24번 게시물 댓글 페이징 처리 1페이지 10개의 댓글 보이지
@Test
  public void testList2(){
    Criteria cri = new Criteria(1,10);
    List<ReplyVO> replies = mapper.getListWithPaging(cri,24);
    replies.forEach(reply->log.info(reply));
  }
}
