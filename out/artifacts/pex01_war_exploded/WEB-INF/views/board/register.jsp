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

                <form role="form" action="/board/register" method="post">
                    <div class="form-group">
                        <label>title</label><input class="form-control" name="title">
                    </div>
                    <div class ="form-group">
                        <label>Text area</label>
                        <textarea class="form-control" rows="3" name="content"></textarea>
                    </div>

                    <div class="form-group">
                        <label>writer</label><input class="form-control" name="writer">
                    </div>

                    <button type="submit" class="btn btn-default">submit button</button>
                    <button type="reset" class="btn btn-default">reset button</button>

                </form>
            </div>
        </div>
    </div>

</div>
<%@include file="../includes/footer.jsp"%>
