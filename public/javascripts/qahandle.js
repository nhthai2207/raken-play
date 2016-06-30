var jsonUrl = '/api/getListQuestion';
$(document).ready(function() {
    //populateQuestion();

    $('#btnSubmit').click(function(){
        submitChoice();
        return false;
    });


});

function submitChoice(){
    if(chkRadio() == false){
        alert("回答が選択されていません");
        return false;
    }
    else{
    	console.log("submit result");
        $('#qaBoard').submit();
    }

}

function chkRadio(){
    if ($('input[name*="option-q"]:checked').length < 3) return false;
    else return true;
}

/*
function callJsonQuestion(postData,jsonUrl,callback){
    var ajaxObj = {
        url: jsonUrl,
        dataType: "json",
        type: postData?"POST":"GET",
        data: postData,
        success: function (data) {
            if(data.statusCode == 101) {
                //tmplUrl = "/assets/template/notfound";
            } else{
                if (callback) callback(data);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    };
    $.ajax(ajaxObj);
}

function populateQuestion(){
    var postData = document.getElementById('playTurnsToken').value;
    console.log(postData);
    callJsonQuestion({postData:postData},jsonUrl,function(data){
        console.log(data);
        populateTemplate(data);

    });
}

function populateTemplate(data){
    console.log(data);
    var qaData = new EJS({url: '/akko_facebook/template_ejs/questionContent.ejs'}).render(data);
    $('#qa-board').append(qaData);

}
*/
