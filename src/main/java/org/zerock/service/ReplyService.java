package org.zerock.service;

import org.zerock.vo.Criteria;
import org.zerock.vo.ReplyPageDTO;
import org.zerock.vo.ReplyVO;

import java.util.List;

public interface ReplyService {

    public int register(ReplyVO vo);
    public ReplyVO get(Long rno);
    public int modify(ReplyVO vo);
    public int remove(int rno);
    public List<ReplyVO> getList(Criteria cri, Long bno);
    public ReplyPageDTO getListPage(Criteria cri, Long bno);
}
