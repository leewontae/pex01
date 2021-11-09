package org.zerock.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.service.BoardService;
import org.zerock.vo.BoardAttachVO;
import org.zerock.vo.BoardVo;
import org.zerock.vo.Criteria;
import org.zerock.vo.PageVo;
import sun.tools.jconsole.JConsole;

import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@Log4j
@RequestMapping(value = "/board/*")
@AllArgsConstructor
// @allARgsConstructor를 이용해서 생성자를 만들고 자동으로 주입하도록 한다.
// 만일 생성자를 만들지 않을 경우 @Setter(onMethod_={@Autowired}를 이용해서 처리 한다.
public class BoardController {

    private BoardService boardService;

/*    @GetMapping("/list")
    public void list(Model model){

        log.info("list");
        model.addAttribute("list",boardService.getList());
  
    }*/
    @GetMapping("/list")
    public void list(Criteria criteria, Model model){
        log.info("list:"+criteria);

        model.addAttribute("list",boardService.getList(criteria));
        //model.addAttribute("pageMaker", new PageVo(criteria,123));
        List<BoardVo> boardvolist = boardService.getList(criteria);
        for(BoardVo vo : boardvolist){

            log.info(  "@@@@@@@@@@@@@@@@@@@@@"+  vo.getReplyCnt());
        }
        int total= boardService.getTotal(criteria);
        log.info("total: "+ total);

        model.addAttribute("pageMaker", new PageVo(criteria,total));
    }


    @GetMapping("/register")
    public String register(){

        return "/board/register";
    }
    @PostMapping("/register")
    public String register(BoardVo boardVo, RedirectAttributes rttr){
        // RedirectAttributes는 등록 작업 이 끝난 후 다시 목록화면으로 이동하기 위함인데, 추가적으로 새롭게 등록괸 게시물의 번호를 같이 전달하기 위해서
        // 사용한다.
        log.info("register:" + boardVo);

        if (boardVo.getAttachList() != null) {

            boardVo.getAttachList().forEach(attach -> log.info(attach));

        }
        log.info("==========================");
        rttr.addFlashAttribute("result", boardVo.getBno());

        boardService.add(boardVo);


        rttr.addFlashAttribute("result",boardVo.getBno());
        // addFlashAttribute() 를 이용해서 일회성 으로만 데이터를 사용할수 있으므로 이를 이용해서 경고창이나 모달창 등을 보여주는 방식

        return "redirect:/board/list";
        //redirect 는 스프링 mvc가 내부적으로 response, sendRedirect()를 처리해 주기 때문에 편리하다.
    }


    @GetMapping({"/get","/modify"})
    public void get (@RequestParam("bno") long bno , @ModelAttribute("cri") Criteria cri, Model model ){
        // @modelAttribute 는  자동으로 Model에 데이터를 지정한 이름으로 담아준다.
        log.info("/get or modify....");
        model.addAttribute("board",boardService.get(bno));

    }
    @PostMapping("/modify")
    public String modify(BoardVo boardVo,@ModelAttribute("cri") Criteria cri, RedirectAttributes rttr){
        log.info("modify"+ boardVo);

        if(boardService.modify(boardVo)){
            rttr.addFlashAttribute("result","success");
        }

        rttr.addAttribute("pageNum",cri.getPageNum());
        rttr.addAttribute("amount",cri.getAmount());
        rttr.addAttribute("type",cri.getType());
        rttr.addAttribute("keyword",cri.getKeyword());
        return "redirect:/board/list";
        //return "redirect:/board/list" + cri.getListLink();
    }
    @GetMapping(value = "/getAttachList", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<BoardAttachVO>> getAttachList(Long bno){

        log.info("getAtttachList" +bno);
        return new ResponseEntity<>(boardService.getAttachList(bno), HttpStatus.OK);
    }

    private void deleteFiles(List<BoardAttachVO> attachList) {

        if(attachList == null || attachList.size() == 0) {
            return;
        }

        log.info("delete attach files...................");
        log.info(attachList);

        attachList.forEach(attach -> {
            try {
                Path file  = Paths.get("C:\\upload\\"+attach.getUploadPath()+"\\" + attach.getUuid()+"_"+ attach.getFileName());

                Files.deleteIfExists(file);

                if(Files.probeContentType(file).startsWith("image")) {

                    Path thumbNail = Paths.get("C:\\upload\\"+attach.getUploadPath()+"\\s_" + attach.getUuid()+"_"+ attach.getFileName());

                    Files.delete(thumbNail);
                }

            }catch(Exception e) {
                log.error("delete file error" + e.getMessage());
            }//end catch
        });//end foreachd
    }


    @PostMapping("/remove")
    public String remove(@RequestParam("bno") long bno , @ModelAttribute("cri") Criteria cri,  RedirectAttributes redirectAttributes){

        log.info("remove....."+bno);
        List<BoardAttachVO> attachList = boardService.getAttachList(bno);

        if(boardService.remove(bno)){
            deleteFiles(attachList);
            redirectAttributes.addFlashAttribute("result","success");
        }

       /*redirectAttributes.addAttribute("pageNum",cri.getPageNum());
        redirectAttributes.addAttribute("amount",cri.getAmount());
        redirectAttributes.addAttribute("type",cri.getType());
        redirectAttributes.addAttribute("keyword",cri.getKeyword());*/
        //return "redirect:/board/list";
        return "redirect:/board/list" +cri.getListLink();
    }


}
