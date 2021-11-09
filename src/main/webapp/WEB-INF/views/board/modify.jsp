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

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">Board Register</div>
            <div class="panel-body">

            <form role="form" action="/board/modify" method="post"><%--수정/삭제 페이지에서 검색처리--%>

                <input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum}"/>'>
                <input type="hidden" name="amount" value='<c:out value="${cri.amount}"/>'>
                <input type="hidden" name="type" value='<c:out value="${cri.type}"/>'>
                <input type="hidden" name="keyword" value='<c:out value="${cri.keyword}"/>'>

                <div class="form-group">
                        <label>bno</label>
                        <input class="form-control" name="bno"
                               value="<c:out value="${board.bno}"/>" readonly="readonly">

                    </div>
                <div class="form-group">
                    <label>title</label>
                    <input class="form-control" name="title"
                           value='<c:out value="${board.title}"/>'>

                </div>
                <div class="form-group">
                    <label>text area</label>
                    <textarea class="form-control" rows="3" name="content" >
                        <c:out value="${board.content}"/>
                </textarea>
                </div>

                    <div class="form-group">
                        <label>writer</label><input class="form-control" name="writer"
                                                    value='<c:out value="${board.writer}"/>'
                                                    readonly="readonly">
                    </div>
    <div class="form-group">
        <label>regdate</label>
        <input class="form-control" name='regDate'
               value='<fmt:formatDate pattern="yyyy/MM/dd" value="${board.regdate}"/>' readonly="readonly" >
    </div>

        <div class="form-group">
            <label>update date</label>
            <input class="form-control" name='updateDate'
                   value='<fmt:formatDate pattern="yyyy/MM/dd" value="${board.updateDate}"/>' readonly="readonly" >
        </div>


        <button type="submit" data-oper='modify' class="btn btn-default">modify</button>
        <button type="submit" data-oper='remove' class="btn btn-default">remove</button>
        <button type="submit" data-oper='list' class="btn btn-info">list</button>
                </form>
            </div>
        </div>
    </div>

</div>
<script type="text/javascript">
    $(document).ready(function (){ //수정/삭제 페이지에서 목록 페이지로 이동 , modify.jsp의 필요한 파라미터만 전송하기위한 태그 내용 다 지우고 다시 투가 하는 방법
       var formObj = $("form");
       $('button').on("click",function(e){
          e.preventDefault();

          var operation = $(this).data("oper");

          console.log(operation);

          if(operation ==='remove'){
              formObj.attr("action","/board/remove");

          }else if(operation ==='list'){
             /* self.location="/board/list";
              return;*/
              //move to list
              formObj.attr("action","/board/list").attr("method","get");
              var pageNumTag = $("input[name='pageNum']").clone();
              var amountTag= $("input[name='amount']").clone();
              var keywordTag= $("input[name='keyword']").clone();
              var typeTag= $("input[name='type']").clone();

              formObj.empty();
              formObj.append(pageNumTag);
              formObj.append(amountTag);
              formObj.append(keywordTag);
              formObj.append(typeTag);
          }
          formObj.submit();
       });
    });
</script>
<%@include file="../includes/footer.jsp"%>
