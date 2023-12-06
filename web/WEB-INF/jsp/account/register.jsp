<%@ include file="../common/top.jsp"%>

<div id="Catalog">
    <form action="register" method="post">
        <p>Please enter your username,password and email.</p>
        <c:if test="${requestScope.registerMsg != null}">
            <p> <font color="red">${requestScope.registerMsg} </font> </p>
        </c:if>
        <p>
            Username:<input type="text" name="username"> <br />
            Password:<input type="password" name="password"> <br />
            ValidationCode:<input type="text" name="validationCode" style="width: 50px;height: 20px"/>
            <img src="ValidateCodeServlet"  id="validationCode" ><br/>
        </p>
        <script>
            $('#validationCode').click(function () {
                $(this).attr('src','csu/web/mypetstore/web/servlet/validateCodeServlet.java?time='+ new Date().getTime());
            });
        </script>
        <input type="submit" value="click">
    </form>

</div>

<%@ include file="../common/bottom.jsp"%>