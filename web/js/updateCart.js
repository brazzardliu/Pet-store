$(function (){
    $('.Quantity').on('keyup',function (){
        var ControlId = $(this).attr('id');
        var price = $(this).data('price');
        var itemId = $(this).data('itemid');
        console.log(ControlId)
        console.log(price)
        console.log(itemId)
        var Quantity = $(this).val();
            $.ajax({
                type    : 'GET',
                url     : 'http://localhost:8080/petStore_war_exploded/updateCartJSServlet?quantity='+Quantity+'&itemid='+itemId,
                success : function (data){
                    console.log(price);
                    totalContent = Quantity * price;
                    var number = ControlId.substring(ControlId.length - 1);
                    var totalId = 'total_'+number;
                    var ControlTotal = document.getElementById(totalId);
                    ControlTotal.textContent = '$'+totalContent;
                }
            })

    })
})