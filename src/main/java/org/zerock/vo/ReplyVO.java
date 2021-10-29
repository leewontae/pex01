package org.zerock.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Data
public class ReplyVO {

    private long rno;
    private long bno;

    private String reply;
    private String replyer;
    private Date replydate;
    private Date updatedate;

}
