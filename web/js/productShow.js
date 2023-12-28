
$(function (){
    document.onmousemove = function (e) {
        var title = document.getElementById("mytitle");
        title.style.left = (e.pageX + 10) + "px";
        title.style.top = (e.pageY + 10) + "px";
    };
    $('#MainImageContent area').mouseenter(function () {
        var categoryId = $(this).attr('alt');
        console.log(categoryId);
        $.ajax({
            type     :'GET',
            url      :'http://localhost:8080/petStore_war_exploded/productShow?categoryId='+categoryId,
            success  : function (data){
                console.log(data);
                var productListHTML='';
                for(var i=0;i<data.length;i++){
                    productListHTML +='<li class=\"productAutoItem\" data-productId="';
                    productListHTML +=data[i].productId;
                    productListHTML += '">' ;
                    productListHTML += data[i].productId;
                    productListHTML +=': '
                    productListHTML += data [i].name;
                    productListHTML += '</li>'
                }
                $('#productShowList').html(productListHTML);
                $('#mytitle').show();
            },
            error : function (errorMsg){
                console.log(errorMsg);
            }
        })
})
    $('#MainImageContent area').mouseleave(function (){
        $('#mytitle').hide();
    });
})

