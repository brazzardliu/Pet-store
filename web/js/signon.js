$(function (){
    $('#username').on('blur',function (){
        var elusername = document.getElementById("username");
        var elLabel1 = document.getElementById("label1");
        if (elusername.value.length >= 8) {
            elLabel1.textContent = "The length of username must more than 8";
        } else {
            elLabel1.textContent = "";
            $.ajax({
                type     :'POST',
                url      : 'http://localhost:8080/petStore_war_exploded/testServlet?username='+$(elusername).val(),
                success  :function (data){
                    console.log(data);
                    //进行数据清洗
                    data = data.trim();
                    if (data === 'true'){
                        elLabel1.textContent = 'The username has been used';
                    }
                    else if (data === 'false'){
                        elLabel1.textContent = 'The username is right';
                    }
                },
                error    : function (errorMsg){
                    console.log(errorMsg);
                }
            })
        }
    })
})