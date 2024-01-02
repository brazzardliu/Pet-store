<%@ include file="../common/top.jsp"%>

<div id="Catalog">
    <form action="register" method="post">
        <p>Please enter your username,password and email.</p>
        <c:if test="${requestScope.registerMsg != null}">
            <p> <font color="red">${requestScope.registerMsg} </font> </p>
        </c:if>
        <p>
            Username:<input type="text" name="username" id="username"> <br />
            <label id="label1"></label><br/>
            Password:<input type="password" name="password"> <br />
            ValidationCode:<input type="text" name="validationCode" style="width: 50px;height: 20px"/>
            <img src="ValidateCodeServlet"  id="validationCode" ><br/>
        </p>
        <input type="submit" value="click">
    </form>

</div>
<script src="js/signon.js"></script>
<%@ include file="../common/bottom.jsp"%>