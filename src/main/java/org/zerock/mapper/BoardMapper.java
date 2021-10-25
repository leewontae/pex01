package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;
import org.zerock.vo.BoardVo;
import org.zerock.vo.Criteria;

import java.util.List;

public interface BoardMapper {

    //@Select("select * from tb1_board where bno> 0")
    public List<BoardVo> getList();
    public List<BoardVo> getListWithPaging(Criteria cri);
    public void insert(BoardVo boardVo);
    public void insertSelectKey(BoardVo boardVo);
    public BoardVo read(long bno);
    public int delete(long bno);
    public int modify(BoardVo vo);
    public int getTotalCount(Criteria cri);
    // mybatis에서 전체 데이터의 개수 처리

}
