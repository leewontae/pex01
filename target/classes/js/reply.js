console.log("Reply Module");


// 즉시 실행 함수 : 함수를 정의하자마자 바로 호출하는 것을 즉시 실행 함수이다.
// 즉시 실행 함수 : 정의 후 이어서 ()를 붙이면 즉시실행 함수가 된다.
// 즉시 실행 함수 : 정의 후 이어서 ()를 붙이면 즉시실행 함수가 된다.
// replyService 변수에 add 이라는 속성에 add라는 속성값을 가진 객체이다.

var replyService=(function(){

    function add(reply,callback, error){
        // 외부에서 replyService.add(객체 , 콜백 )를 전달하는 형태로 호출한다.
        console.log("add reply..........");

        $.ajax({
            type:'post',
            url:'/replies/new',
            data:JSON.stringify(reply),
            //입력값을 json 문자열로 변환한다.
            contentType:"application/json; charset=utf-8",
            success: function(result,status,xhr){
        if(callback){
            callback(result);
        }
            },
            error: function(xhr,status,er){
                if(error){
                    error(er)
                }
            }
        })
    }
    function getList(param,callback,error){

        var bno = param.bno;
        var page = param.page || 1;

        $.getJSON("/replies/pages/" + bno +"/" +page +".json", function(data){
            //첫번째 매개변수로 json파일 로드 한 후 두번째 매개변수에서 json파일을 이용하여 로드된 데이터를 처리한다.
            //콜백함수는 로드된 데이터를 인자로 넘겨받는다.
            if(callback){
               // callback(data); //댓글 목록만 가져오는 경우
                callback(data.replyCnt,data.list);// 댓글 숫자와 목록을 가져오는 경우
            }
        }).fail(function(xhr,status,err){
                if(err){
                    error();
                }
        });
    }

    function remove(rno,callback, error){
        $.ajax({
            type:'delete',
            url:'/replies/' +rno,
            success: function(deleteResult,status,xhr){
                if(callback){
                    callback(deleteResult);
                }
            },
            error : function(xhr,status,er){
                if(er){
                    error(er);
                }
            }
        });
    }

    function update (reply,callback,error){
        console.log("RNO"+ reply.rno);
        $.ajax({
            type:'put',
            url:'/replies/'+reply.rno,
            data: JSON.stringify(reply),
            contentType: "application/json; charset=utf-8",
            success: function(result,status,xhr){
                console.log("@@@@@@@reply@@@@@"+ result);
                console.log("@@@@@@@reply@@@@@"+ callback);
                if(callback){
                    callback(result);
                    // callback 함수파라미터인 result부분에 callback(result)가 들어가서 실행되다.
                    //callback사용해야 하는 이유: 콜백과정이 끝나기 전에 다음 프로세스를 진행하게 되는 경우
                    // 예를 들어 실제로 db에 값을 읽어들일 때에 값을 읽기전에 출력해버리면 updefind만 뜨는 경우가 있다.
                }
            },error: function(xhr,status,er){
                if(er){
                    error(er);
                }
            }
        });
    }

    function get(rno, callback, error){
        $.get("/replies/"+ rno + ".json", function(result){
            if(callback){
                callback(result);
            }
        }).fail(function(xhr,status,err){
            if(error){
                error();
            }
        });
    }
    function displayTime(timeValue){
         var today = new Date();
         var gap = today.getTime() - timeValue;
         var dateObj = new Date(timeValue);
         var str="";

         if(gap< (1000*60*60*24)) {
             var hh = dateObj.getHours();
             var mi = dateObj.getMinutes();
             var ss = dateObj.getSeconds();

             return [(hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi, ':', (ss > 9 ? '' : '0') + ss].join('');
         }else{
             var yy= dateObj.getFullYear();
             var mm = dateObj.getMonth()+1; // getmonth는 0부터 시작 한다.
             var dd = dateObj.getDate();

             return [yy,'/',(mm>9?'':'0')+mm,'/',(dd>9?'':'0')+dd].join('');

         }
    };
    return {
        add:add,
        getList:getList,
        remove:remove,
        update:update,
        get:get,
        displayTime :displayTime

    };
})();


