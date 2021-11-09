<%--
  Created by IntelliJ IDEA.
  User: iwontae
  Date: 2021/10/24
  Time: 11:35 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../includes/header.jsp"%>

<div class="row">
    <div class="col-lg-12">
        <h1 class ="page-header">Board Register</h1>
    </div>
</div>
<style>
    .uploadResult {
        width:100%;
        background-color: gray;
    }
    .uploadResult ul{
        display:flex;
        flex-flow: row;
        justify-content: center;
        align-items: center;
    }
    .uploadResult ul li {
        list-style: none;
        padding: 10px;
        align-content: center;
        text-align: center;
    }
    .uploadResult ul li img{
        width: 100px;
    }
    .uploadResult ul li span {
        color:white;
    }
    .bigPictureWrapper {
        position: absolute;
        display: none;
        justify-content: center;
        align-items: center;
        top:0%;
        width:100%;
        height:100%;
        background-color: gray;
        z-index: 100;
        background:rgba(255,255,255,0.5);
    }
    .bigPicture {
        position: relative;
        display:flex;
        justify-content: center;
        align-items: center;
    }

    .bigPicture img {
        width:600px;
    }

</style>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">

            <div class="panel-heading">Files</div>
            <!-- /.panel-heading -->
            <div class="panel-body">

                <div class='uploadResult'>
                    <ul>
                    </ul>
                </div>
            </div>
            <!--  end panel-body -->
        </div>
        <!--  end panel-body -->
    </div>
    <!-- end panel -->
</div>
<!-- /.row -->

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">Board Register</div>
            <div class="panel-body">


                    <div class="form-group">
                        <label>bno</label>
                        <input class="form-control" name="bno" value="<c:out value="${board.bno}"/>" readonly="readonly">

                    </div>
                <div class="form-group">
                    <label>title</label>
                    <input class="form-control" name="title" value="<c:out value="${board.title}"/>" readonly="readonly">

                </div>
                <div class="form-group">
                    <label>text area</label>
                    <textarea class="form-control" rows="3" name="content"  readonly="readonly"><c:out value="${board.content}"/>

                </textarea>
                </div>
                    <div class="form-group">
                        <label>writer</label><input class="form-control" name="writer" value="<c:out value="${board.writer}"/>"
                                                    readonly="readonly">
                    </div>

                 <%--   <button data-oper ="modify" class="btn btn-default"
                    onclick="location.href='/board/modify?bno=<c:out value="${board.bno}"/>'"> modify</button>
                <button data-oper ="list" class="btn btn-default"
                onclick="location.href='/board/list'">list</button>--%>
                <button data-oper='modify' class="btn btn-default">Modify</button>
                <button data-oper='list' class="btn btn-info">List</button>

                <form id="operForm" action="/board/modify" method="get"> <%--조회 페이지에서 검색 처리 --%>
                    <input type="hidden" id="bno" name="bno" value='<c:out value="${board.bno}"/>'>
                    <input type="hidden" name="pageNum" value="<c:out value='${cri.pageNum}'/>">
                    <input type="hidden" name="amount" value="<c:out value='${cri.amount}'/>">
                    <input type="hidden" name="keyword" value='<c:out value="${cri.keyword}"/>'>
                    <input type="hidden" name="type" value='<c:out value="${cri.type}"/>'>
                </form>

            </div>
        </div>
    </div>

</div>
<div class='bigPictureWrapper'>
    <div class='bigPicture'>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-comments fa-fw"></i> Reply
                <button id="addReplyBtn" class="btn btn-primary btn-xs pull-right"> new reply</button>
            </div>
            <div class="panel-body">
                <ul class="chat">
                    <li class="left clearfix" data-rno="12">
                        <div>
                            <div class="header">
                                <strong class="primary-font">user00</strong>
                                <small class="pull-right text-muted">2018-01-01 13:13</small>
                            </div>
                            <p>Good job!</p>
                        </div>
                    </li>
                </ul>

            </div>
            <div class="panel-footer">

            </div>
        </div>
    </div>

</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
            </div>
            <div class="model-body">
                <div class="form-group">
                    <label>Reply</label>
                    <input class="form-control" name='reply' value="new Reply!!!!">
                </div>
                <div class="form-group">
                    <label>Replyer</label>
                    <input class="form-control" name='replyer' value="replyer">
                </div>
                <div class="form-group">
                    <label>Reply date</label>
                    <input class="form-control" name='replydate' value="">
                </div>
            </div>
            <div class="modal-footer">
                <button id="modalModBtn" type="button" class="btn btn-warning">Modify</button>
                <button id="modalRemoveBtn" type="button" class="btn btn-danger">Remove</button>
                <button id="modalRegisterBtn" type="button" class="btn btn-default" >Register</button>
                <button id="modalCloseBtn" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>


</div>
<%@include file="../includes/footer.jsp"%>
<script type="text/javascript" src="/resources/js/reply.js" ></script>
<%--list.jsp에서 get.jsp 로 들어올때 reply 안에 있는 파일을 읽어 온다. --%>

<script>
    console.log("============");
    console.log("JS TEST");
    var bnoValue= '<c:out value="${board.bno}"/>';
    console.log(bnoValue);

/*    // 추가

    replyService.add({reply:"JS TEST", replyer:"tester",bno:bnoValue},
        function(result){
    alert("RESULT:"+result);

    });*/

/*   // 댓글 list 조회

    replyService.getList({bno:bnoValue,page:1},function(list){
        for(var i=0, len=list.length ||0; i<len; i++){
            console.log("댓글 목록 확인 하는 곳 "+list[i]);
        }

    });*/

/*
    // 댓글 삭제와 갱신
        remove에 숫자는 rno의 숫자이므로 한번 삭제 하고 난후에는 없으므로 같은번호 두먼 이상 할시 오류
        replyService.remove(2,function(count){
            console.log(count);
            if(count ==="success"){
                alert("REMOVED");
            }},function(err){
            alert('error,,,');
        });
*/

    //replyService.update({rno:22,bno:bnoValue,reply: "modified reply..."},function(result){alert("수정 완료"); })

   // replyService.get(24,function(result){console.log(result);});

</script>
<script type="text/javascript">

    $(document).ready(function(){
//  $(document).ready(...)는 한페이지 내에서 여러번 나와도 상관없다.
        console.log(replyService);

        function showImage(fileCallPath){

            alert(fileCallPath);

            $(".bigPictureWrapper").css("display","flex").show();

            $(".bigPicture")
                .html("<img src='/display?fileName="+fileCallPath+"' >")
                .animate({width:'100%', height: '100%'}, 1000);

        }
        (function(){

            var bno = '<c:out value="${board.bno}"/>';

            /* $.getJSON("/board/getAttachList", {bno: bno}, function(arr){

              console.log(arr);


            }); *///end getjson
            $.getJSON("/board/getAttachList", {bno: bno}, function(arr){

                console.log(arr);

                var str = "";

                $(arr).each(function(i, attach){

                    //image type
                    if(attach.fileType){
                        var fileCallPath =  encodeURIComponent( attach.uploadPath+ "/s_"+attach.uuid +"_"+attach.fileName);
                     alert("fileCallPath : "+fileCallPath)
                        str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
                        str += "<img src='/display?fileName="+fileCallPath+"'>";
                        str += "</div>";
                        str +"</li>";
                    }else{

                        str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
                        str += "<span> "+ attach.fileName+"</span><br/>";
                        str += "<img src='/resources/img/20-11.jpg'></a>";
                        str += "</div>";
                        str +"</li>";
                    }
                });

                $(".uploadResult ul").html(str);


            });//end getjson


        })();//end function

        $(".uploadResult").on("click","li", function(e){

            console.log("view image");

            var liObj = $(this);

            var path = encodeURIComponent(liObj.data("path")+"/" + liObj.data("uuid")+"_" + liObj.data("filename"));

            if(liObj.data("type")){
                showImage(path.replace(new RegExp(/\\/g),"/"));
            }else {
                //download
                self.location ="/download?fileName="+path
            }


        });


        $(".bigPictureWrapper").on("click", function(e){
            $(".bigPicture").animate({width:'0%', height: '0%'}, 1000);
            setTimeout(function(){
                $('.bigPictureWrapper').hide();
            }, 1000);
        });

        var replyUL = $(".chat");

        showList(1);

        function showList(page){

            console.log("show list" + page);
            replyService.getList({bno:bnoValue,page:page||1},function(replyCnt,list){

                console.log("replyCnt:" + replyCnt);
                console.log("list:" + list);
                console.log(list);

                if(page == -1){ // page 번호가 -1로 전달되면 마지막 페이지를 찾아서 다시 호출 한다.
                    pageNum = Math.ceil(replyCnt/10.0);
                    showList(pageNum);
                    return;
                }

                var str = "";
                if(list == null || list.length == 0){
                replyUL.html("");
                return;
                }
                for(var i=0, len=list.length || 0; i<len; i++){
                    str+="<li class='left clearfix' data-rno='"+list[i].rno+"'>";
                    str+="<div><div class='header'><strong class='primary-font'>"+list[i].replyer+"</strong>";
                    //str+="<small class='pull-right text-muted'>"+list[i].replydate+"</small></div>";
                    str+="<small class='pull-right text-muted'>"+replyService.displayTime(list[i].replydate)+"</small></div>";
                    str+="<p>"+list[i].reply+"</p></div></li>";
                }
                replyUL.html(str);

                showReplyPage(replyCnt);
            });
        }
        var modal=$(".modal");
        var modalInputReply  = modal.find("input[name='reply']");
        var modalInputReplyer  = modal.find("input[name='replyer']");
        var modalInputReplyDate  = modal.find("input[name='replydate']");

        var modalModBtn =$("#modalModBtn");
        var modalRemoveBtn =$("#modalRemoveBtn");
        var modalRegisterBtn =$("#modalRegisterBtn")

        $("#addReplyBtn").on("click",function(e){
           modal.find("input").val("");
           modalInputReplyDate.closest("div").hide();
           modal.find("button[id != 'modalCloseBtn']").hide();

           modalRegisterBtn.show();

           $(".modal").modal("show");
        });
        modalRegisterBtn.on("click",function(e){
           var reply={
               reply:modalInputReply.val(),
               replyer:modalInputReplyer.val(),
               bno:bnoValue
           } ;
           replyService.add(reply,function(result){
              alert(result);
                modal.find("input").val("");
               modal.modal("hide");

               showList(1);
           });
        });

        // 댓글 클릭시 상세보기
        $(".chat").on("click","li",function(e){
           var rno = $(this).data("rno");

           //console.log(rno);
            replyService.get(rno,function(reply){
               modalInputReply.val(reply.reply);
               modalInputReplyer.val(reply.replyer);
               modalInputReplyDate.val(replyService.displayTime(reply.replydate)).attr("readonly","readonly");
               modal.data("rno",rno);

               modal.find("button[id != 'modalCloseBtn']").hide();
               modalModBtn.show();
               modalRemoveBtn.show();

               $(".modal").modal("show");
            });
        });

        modalModBtn.on("click",function(e){

            var reply = {rno:modal.data("rno"),reply:modalInputReply.val()};

            console.log("@@@@@get.jsp@@@@@@@@"+reply)
            replyService.update(reply,function(result){
              //  alert("@@@@@get.jsp@@@@@"+result);
                console.log("@@@@@@@@@get.jsp"+ result);
                modal.modal("hide");
                //showList(1);
                showList(pageNum); // 수정 시에도 현재 댓글이 포함된 페이지로 이동한다.
            });

        });

        modalRemoveBtn.on('click',function(e){
            var  rno = modal.data("rno");

            replyService.remove(rno,function(result){
               alert(result);

               modal.modal("hide");
               //showList(1);
                showList(pageNum);// 삭제 시에도 현재 댓글이 포함된 페이지로 이동한다.
            });
        });

        modalRegisterBtn.on("click",function(e){
           var reply={
               reply : modalInputReply.val(),
               replyer: modalInputReplyer.val(),
               bno:bnoValue
           } ;
           replyService.add(reply,function(result){
              alert(result);
              modal.find("input").val("");
              modal.modal("hide");
              showList(-1);
           });
        });

        var pageNum = 1;
        var replyPageFooter = $(".panel-footer");

        function showReplyPage(replyCnt){
            console.log("replyCnt"+ replyCnt);
            var endNum = Math.ceil(pageNum/10.0)*10;
            console.log("endnum"+ endNum);
            var startNum = endNum -9;
            console.log("startNum"+ startNum);
            var prev = startNum != 1;
            var next = false;

            if(endNum * 10 >= replyCnt){
                endNum =Math.ceil(replyCnt/10.0);
            }
            if(endNum * 10 < replyCnt){
                next =true;
            }

            var str="<ul class='pagination pull-right'>";

            if(prev){
                str+="<li class='page-item'><a class='page-link' href='"+(startNum-1)+"'>Previous</a></li>";
            }
            for(var i=startNum; i<=endNum; i++){
                var active = pageNum == i? "active":"";
                str+="<li class='page-item "+active+"'><a class='page-link' href='"+i+"'>"+i+"</a></li>";
            }
            if(next){
                str+="<li class='page-item'><a class='page-link' href='"+(endNum+1)+"'>Next</a></li>";
            }
            str +="</ul></div>";
            console.log(str);

            replyPageFooter.html(str);
        }
    replyPageFooter.on("click","li a",function(e){
        //댓글페이징처리 후 1페이지에서 2페이지 누렀을때 넘어가게 하는 코드
       e.preventDefault(); //a 태그의 기본 동작을 제어 한다.
       console.log("page click");

       var targetPageNum = $(this).attr("href");
       console.log("targetPageNum: " + targetPageNum);
       pageNum = targetPageNum;
       showList(pageNum);
    });

    });
</script>
<script type="text/javascript" >

    $(document).ready(function (){

       var operForm =$("#operForm");
       $("button[data-oper='modify']").on("click",function(e){
           operForm.attr("action","/board/modify").submit();
       });
       //button[data-oper='modify'] : jquery로 data-oper로 접근
        $("button[data-oper='list']").on("click",function(e){
           operForm.find("#bno").remove();
           operForm.attr("action","/board/list");
            operForm.submit();
        });
    });

</script>