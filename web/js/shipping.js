$(function (){

    $("#shippingAddressRequired").on("change", function () {
        var change = $("#shippingAddressRequired").is(':checked');
        if (change) {
            $('.Shipping').show();
        }else{
            $('.Shipping').hide();
        }
    });
});