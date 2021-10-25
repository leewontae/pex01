package org.zerock.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVo {

    private int total;
    private int startPage;
    private int endPage ;
    private boolean prev, next;
    private Criteria cri;

    public PageVo(Criteria criteria, int total){
        this.cri = criteria;
        this.total = total;

        this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0))* 10;
        this.startPage = this.endPage -9;
        int realEnd = (int)(Math.ceil((total*1.0)/cri.getAmount()));
        //amount : 페이지에 보여질 데이터 수 , total : 전체 수

        if(realEnd<this.endPage){
            this.endPage = realEnd;
        }

        this.prev = this.startPage>1;
        this.next = this.endPage<realEnd;
    }
}
