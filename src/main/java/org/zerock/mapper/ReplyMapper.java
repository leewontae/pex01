package org.zerock.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.vo.Criteria;
import org.zerock.vo.ReplyVO;

import java.util.List;

public interface ReplyMapper {

public int insert(ReplyVO vo);

public ReplyVO read(Long rno);

public int delete(int rno);

public int update(ReplyVO vo);

public List<ReplyVO> getListWithPaging(
        @Param("cri")Criteria cri,
        @Param("bno")long bno
        );
public int getCountByBno(Long bno);
}
