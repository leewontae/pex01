<%--
  Created by IntelliJ IDEA.
  User: iwontae
  Date: 2021/10/24
  Time: 9:49 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="../includes/header.jsp"%>
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Tables</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Board list page
                        <button id="regBtn" type="button" class="btn btn-xs pull-right">register new board</button>
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">

                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                <tr>
                                    <th>#번호</th>
                                    <th>제목</th>
                                    <th>작성자</th>
                                    <th>작성일</th>
                                    <th>수정일</th>
                                </tr>
                                </thead>
<c:forEach items="${list}" var="board">

    <tr>
        <td><c:out value="${board.bno}"/></td>
        <td><a class='move' href='<c:out value="${board.bno}"/>'><c:out value="${board.title}"/></a></td>
        <td><c:out value="${board.writer}"/></td>
        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate}"/></td>
        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate}"/></td>
    </tr>
</c:forEach>
                            </table>

                        <div class="'row">
                            <div class="col-lg-12">

                                <form id="searchForm" action="/board/list" method="get">
                                    <select name="type">
                                        <option value="" <c:out value="${pageMaker.cri.type==null?'selected':''}"/>>--</option>
                                        <option value="T" <c:out value="${pageMaker.cri.type eq 'T' ?'selected':''}"/>>제목</option>
                                        <option value="C" <c:out value="${pageMaker.cri.type eq 'C'?'selected':''}"/>>내용</option>
                                        <option value="W" <c:out value="${pageMaker.cri.type eq 'W'?'selected':''}"/>>작성자</option>
                                        <option value="TC" <c:out value="${pageMaker.cri.type eq 'TC'?'selected':''}"/>>제목 or 내용</option>
                                        <option value="TW" <c:out value="${pageMaker.cri.type eq 'TW'?'selected':''}"/>>제목 or 작성자</option>
                                        <option value="TWC" <c:out value="${pageMaker.cri.type eq 'TWC'?'selected':''}"/>>제목 or 작성자 or 내용</option>
                                       <%-- <option value="">--</option>
                                        <option value="T">제목</option>
                                        <option value="T">내용</option>
                                        <option value="T">작성자</option>
                                        <option value="T">제목 or 내용</option>
                                        <option value="T">제목 or 작성자</option>
                                        <option value="T">제목 or 제목 or 작성자</option>--%>
                                    </select>
                                    <input type="text" name="keyword" value="<c:out value="${pageMaker.cri.keyword}"/> "/>
                                    <input type="hidden" name="pageNum" value="<c:out value="${pageMaker.cri.pageNum}"/> "/>
                                    <input type="hidden" name="amount" value="<c:out value="${pageMaker.cri.amount}"/> "/>
                                    <button class="btn btn-default">Search</button>

                                </form>


                            </div>

                        </div>

                        <form id="actionForm" action="/board/list" method="get"> <%--검색할 경우 검색 조건과 키워드도 같이 전달되어야 한다. --%>
                            <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
                            <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
                            <input type="hidden" name="type" value="<c:out value='${pageMaker.cri.type}'/>">
                            <input type="hidden" name="keyword" value="<c:out value='${pageMaker.cri.keyword}'/>">
                        </form>
<div class ='pull-right'>
    <ul class ="pagination">

        <c:if test ="${pageMaker.prev}">
            <li class="paginate_button previous"><a href="${pageMaker.startPage-1}">Previous</a></li>
        </c:if>

        <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
            <li class="paginate_button ${pagemMaker.cri.pageNum ==num ? "active":""}"><a href="${num}">${num}</a>
            </li>
        </c:forEach>

        <c:if test ="${pageMaker.next}">
            <li class="paginate_button next"><a href="${pageMaker.endPage+1}">Next</a></li>
        </c:if>

    </ul>
</div>
                        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                                    </div>
                                    <div class="modal-body">
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                        <button type="button" class="btn btn-primary">Save changes</button>
                                    </div>
                                </div>
                                <!-- /.modal-content -->
                            </div>
                            <!-- /.modal-dialog -->
                        </div>
                        <!-- /.modal -->

                        </div>
                        <!-- /.table-responsive -->
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
        </div>
        <!-- /.row -->
<%@include file="../includes/footer.jsp"%>
<script type="text/javascript">
    $(document).ready(function (){
       var result ='<c:out value="${result}"/>';
       checkModal(result);

       history.replaceState({},null,null);

       function checkModal(result){
           if(result ==='' || history.state){
               return;
           }
           if(parseInt(result)>0){
               $(".modal-body").html("게시글" + parseInt(result)+ "번이 등록되었습니다.");
           }
           $("#myModal").modal("show");
       }

       $("#regBtn").on('click',function (){
          self.location="/board/register";
       });

       var actionForm = $("#actionForm");

       /* <form id="actionForm" action="/board/list" method="get">
            <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
                <input type="hidden"   name="amount" value="${pageMaker.cri.amount}">
        </form>*/

       $(".paginate_button a").on("click",function(e) {
           e.preventDefault();
           //a 태그 작동 막기
           console.log('click');
           actionForm.find("input[name='pageNum']").val($(this).attr("href"));
           actionForm.submit();
           // 해줘야 페이지 이동 한다.
        });

       $(".move").on("click",function(e){
           //list.jsp 게시물 조회를 위한 이벤트 처리 추가

           e.preventDefault();
           actionForm.append("<input type='hidden' name='bno' value='" + $(this).attr("href")+"'>");
           actionForm.attr("action","/board/get");
           actionForm.submit();
        });
        var searchForm =$("#searchForm");
        $("#searchForm button").on('click',function (e){

            if(!searchForm.find("option:selected").val()){
                alert("검색종류를 선택하세요");
                return false;
            }
            if(!searchForm.find("input[name='keyword']").val()){
                alert("키워드를 입력하세요");
                return false;
            }
            searchForm.find("input[name='pageNum']").val("1");
            e.preventDefault();
            searchForm.submit();
        });
    });
</script>