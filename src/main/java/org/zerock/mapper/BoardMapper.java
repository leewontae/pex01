package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;
import org.zerock.vo.BoardVo;

import java.util.List;

public interface BoardMapper {

    //@Select("select * from tb1_board where bno> 0")
    public List<BoardVo> getList();
}
