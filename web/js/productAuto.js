
$(function (){
   $('#keyword').on('keyup',function(){
       var keyword= $(this).val();
       if (keyword !==''&&keyword !==null&& keyword.length!==0){
       $.ajax({
           type     :'GET',
           url      :'http://localhost:8080/petStore_war_exploded/productAuto?keyword='+keyword,
           success  : function (data){
                console.log(data);
                var productListHTML='';
                for(var i=0;i<data.length;i++){
                    productListHTML +='<li class=\"productAutoItem\" data-productId="';
                    productListHTML +=data[i].productId;
                    productListHTML += '">' ;
                    productListHTML += data[i].categoryId;
                    productListHTML +=': '
                    productListHTML += data [i].name;
                    productListHTML += '</li>'
                }
                $('#productAutoList').html(productListHTML);
                $('#ProductAutoComplete').show();
           },
           error : function (errorMsg){
               console.log(errorMsg);
           }
       })}else
       {
           $('#ProductAutoComplete').hide();
       }
   });

   // $('.productAutoItem').on('click',function (){
    //        console.log('aaa');
    //   })
    $(document).on('click','.productAutoItem',function () {
      var productId =$(this).data('productid');
      console.log(productId);
        $('#ProductAutoComplete').hide();
        $('#keyword').val('');
        window.location.href='http://localhost:8080/petStore_war_exploded/productForm?productId='+productId;
    });
    $('#ProductAutoComplete').on('mouseleave',function (){
        $(this).hide();
        $('#keyword').val('');
    });

});