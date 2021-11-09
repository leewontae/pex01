package org.zerock.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
@ToString
public class Criteria {

    private int pageNum;
    private int amount;

    private String type;
    private String keyword;

    public Criteria(){
        this(1,10);
    }
    public Criteria(int pageNum, int amount){
        this.pageNum = pageNum;
        this.amount = amount;
    }

    public String[] getTypeArr(){ // 검색 조건이 각 글자(t,w,c)로 구성되어 있으므로 검색 조건을 배열로 만들어서 한번에 처리하기 위함이다.
        // getTypeArr()을 이용해서 mybatis의 동적태그를 활용할 수 있다.
        return type==null? new String[] {}:type.split("");
    }

    public String getListLink(){ //url
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
                .queryParam("pageNum",this.pageNum)
                .queryParam("amount",this.getAmount())
                .queryParam("type",this.getType())
                .queryParam("keyword",this.getKeyword());

        return builder.toUriString();

    }
}
