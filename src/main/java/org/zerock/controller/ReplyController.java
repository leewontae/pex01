package org.zerock.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.service.ReplyService;
import org.zerock.vo.Criteria;
import org.zerock.vo.ReplyPageDTO;
import org.zerock.vo.ReplyVO;

import java.util.List;

@RequestMapping(value = "/replies/")
@RestController
@Log4j
@AllArgsConstructor
public class ReplyController {

    private ReplyService service;

    @PostMapping(value = "/new", consumes = "application/json",produces = {MediaType.TEXT_PLAIN_VALUE})
    //consumes : content-type 요청 헤더가 consumes에 지정한 미디어타입과 일치할떄만 요청이 매칭할것이다.
    public ResponseEntity<String> create(@RequestBody ReplyVO vo){
        // @RequestBody를 사용하여 json데이터를 ReplyVO 타입으로 변환하도록 지정한다.
        log.info("ReplyVo: " + vo);
        int insertCount = service.register(vo);

        log.info("Reply insert count:"+ insertCount);

        return insertCount ==1 ? new ResponseEntity<>("success", HttpStatus.OK): new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        // 상항 연상자 처리 , HttpStatus : http상태코드
    }

    @GetMapping(value = "/pages/{bno}/{page}",produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    // produces는 클라이언트 요청에 따른 결과값을 어떤 방식으로 표현 할지 정한다
    // consumees는 클라이언트가 서버에게 어떤 방식으로 표현 할지 정한다.
    // xml이나 json형태로 서버에서 클라이언트로 못보낼시 라이브러리 확인 할것
    public ResponseEntity<ReplyPageDTO> getList(@PathVariable("page") int page, @PathVariable("bno") Long bno){
        //여기서 @pathVariable(매핑이름) 실제 값이므로 URL의 page와 매핑이름 page가 같아야 한다.  이 어노테이션은 rest apl
        log.info("getList.......");
        Criteria cri = new Criteria(page,10);
        log.info("get reply list bno:"+ bno);
        log.info("cri"+ cri);
        return new ResponseEntity<>(service.getListPage(cri,bno),HttpStatus.OK);
    }

    @GetMapping(value = "/{rno}", produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno){
        log.info("Get: "+rno);
        return new ResponseEntity<>(service.get(rno),HttpStatus.OK);
    }
    @DeleteMapping(value = "/{rno}",produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> remove(@PathVariable("rno") int rno){
        log.info("remove"+ rno);

        return service.remove(rno) ==1
                ? new ResponseEntity<>("success",HttpStatus.OK):new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = {RequestMethod.PUT,RequestMethod.PATCH},
            value = "/{rno}",
            consumes = "application/json" ,
                    produces={MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> modify(@RequestBody ReplyVO vo, @PathVariable("rno") Long rno){
        //json,xml을 받아와 자바객체로 변환 해준다.
        vo.setRno(rno);
        log.info("rno: " + rno);
        log.info("modify: " + vo);

        return service.modify(vo) ==1? new ResponseEntity<>("success",HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
