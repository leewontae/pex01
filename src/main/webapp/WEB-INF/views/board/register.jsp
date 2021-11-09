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
<style>

    .uploadResult{
        width:100%;
        background-color: gray;
    }
    .uploadResult ul{
        display: flex;
        flex-flow:row;
        justify-content:center;
        align-items: center;
    }
    .uploadResult ul li{
        list-style: none;
        padding:10px;
        align-content: center;
        text-align: center;
    }
    .uploadResult ul li img{
        width: 100px;
    }
    .uploadResult ul li span{
        color: white;
    }
    .bigPictureWrapper{
        position: absolute;
        display: none;
        justify-content: center;
        align-items: center;
        top: 0%;
        width: 100%;
        height: 100%;
        background-color: gray;
        z-index: 100;
        background: rgba(255,255,255,0.5);

    }
    bigPicture{
        position: relative;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .bigPicture img{
        width: 600px;
    }
</style>

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

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading"> File Attach</div>
            <div class="panel-body">
               <div class="form-group uploadDiv">
                   <input type="file" name="uploadFile" multiple>
               </div>
               <div class="uploadResult">
                  <ul>

                 </ul>
               </div>
            </div>
        </div>
    </div>
</div>
<script>
$(document).ready(function(){

    var  fromObj = $("form[role='form']");
    $("button[type='submit']").on("click", function(e){

        e.preventDefault();

        console.log("submit clicked");

        var str = "";

        $(".uploadResult ul li").each(function(i, obj){

            var jobj = $(obj);

            console.dir(jobj);
            console.log("-------------------------");
            console.log(jobj.data("filename"));


            str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
            str += "<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
            str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
            str += "<input type='hidden' name='attachList["+i+"].fileType' value='"+ jobj.data("type")+"'>";

        });

        console.log(str);

        fromObj.append(str).submit();

    });

    /*파일 업로드 작업*/
    var regex = new RegExp("(.*?)\.(exe\sh\zip\alz)$");
    var maxSize = 5242880; //5mb

    function checkExtension(fileName, fileSize){
        if(fileSize >= maxSize){
            alert("파일 사이즈 초과");
            return false;
        }
        if(regex.test(fileName)){
            alert("해당 종류의 파일은 업로드할 수 없다. ")
            return false;
        }
        return true;
    }

    function showUploadResult(uploadResultArr){
        if(!uploadResultArr || uploadResultArr.length ==0){return;}

        var uploadUL = $(".uploadResult ul");
        var str="";
         $(uploadResultArr).each(function(i,obj){
             console.log("@@@@@@@@@"+obj.image);
             if(obj.image){
                var fileCallPath = encodeURIComponent(obj.uploadPath+"/s_"+obj.uuid+"_"+obj.fileName);
                str += "<li data-path='"+obj.uploadPath+"'";
                str += " data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'";
                str += "><div>";
                str += "<span> " + obj.fileName+"</span>";
                str += "<button type='button' data-file=\'"+ fileCallPath+"\'";
                str += "data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
                str +="<img src='/display?fileName="+fileCallPath+"'>";
                str +="</div>";
                str +="</li>";
             }else{
                    var fileCallPath= encodeURIComponent(obj.uploadPath+"/"+obj.uuid+"_"+obj.fileName);
                    var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");

                 str += "<li ";
                 str += "data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"' ><div>";
                 str += "<span> "+ obj.fileName+"</span>";
                 str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='file'";
                 str += "class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
                 str += "<img src='/resources/img/20-11.jpg'></a>";
                 str += "</div>";
                 str += "</li>";

             }
         });
         uploadUL.append(str);
    }

    $("input[type='file']").change(function(e){

        var formData = new FormData();
        var inputFile = $("input[name='uploadFile']");
        console.log("inputFile : {} ",inputFile)

        var files = inputFile[0].files;
        console.log("files : {}", files)

        for(var i=0; i<files.length; i++){
            if(!checkExtension(files[i].name, files[i].size)){// 위에 함수 정의됨
                return false;
            }
            formData.append("uploadFile",files[i]);
        }

        $.ajax({
            url:'/uploadAjaxAction',
            processData: false,
            contentType: false,
            data:formData,
            type:"post",
            dataType:'json',
            success:function(result){
               console.log("asdasdasdsad"+result);
               showUploadResult(result); // 업로드 결과 처리 함수
            }
        });
    });

    $(".uploadResult").on("click","button",function(e){
       console.log("delete file");

       var targetFile = $(this).data("file");
       var type= $(this).data("type");

       var targetLi = $(this).closest("li");

       $.ajax({
           url:'/deleteFile',
           data: {fileName: targetFile, type:type},
           dataType: 'text',
           type:'post',
           success:function(result){
               alert(result);
               targetLi.remove();
           }
       })
    });
});

</script>
<%@include file="../includes/footer.jsp"%>
