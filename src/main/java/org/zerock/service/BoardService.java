package org.zerock.service;

import org.zerock.vo.BoardVo;
import org.zerock.vo.Criteria;

import java.util.List;

public interface BoardService {
    public void add(BoardVo boardVo);
    public BoardVo get(long bno);
    public boolean modify(BoardVo boardVo);
    public boolean remove(long bno);
    public List<BoardVo> getList();
    public List<BoardVo> getList(Criteria criteria);
    public int getTotal(Criteria cri);
}
