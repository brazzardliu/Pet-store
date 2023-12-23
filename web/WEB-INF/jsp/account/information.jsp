<%@ include file="../common/top.jsp"%>

<style>
    #Catalog {
        text-align: left;
    }
</style>
<div id="Catalog">
    <form action="information" method="post">
        <p>Please enter your information.</p>
        <c:if test="${requestScope.informationMsg != null}">
            <p> <font color="red">${requestScope.informationMsg} </font> </p>
        </c:if>
        <p>
            First name:<input type="text" name="account.firstName"> <br />
            Last name:<input type="text" name="account.lastName"> <br />
            Phone:<input type="tel" name="account.phone"> <br />
            Email:<input type="email" name="account.email" size="40"> <br />
            Address 1:<input type="text" name="account.address1" size="40"> <br />
            Address 2:<input type="text" name="account.address2" size="40"> <br />
            City:<input type="text" name="account.city"> <br />
            State:<input type="text" name="account.state" size="4"> <br />
            Zip:<input type="text" name="account.zip" size="10"> <br />
            Country:<input type="text" name="account.country" size="15"> <br />
            langpref:<input type="text" name="account.langpref"> <br />
            favcategory:<input type="text" name="account.favcategory"> <br />
        </p>
        <input type="submit" value="Confirm and return to login">
    </form>

</div>

<%@ include file="../common/bottom.jsp"%>
