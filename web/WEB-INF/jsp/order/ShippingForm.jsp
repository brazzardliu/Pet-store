<%@ include file="../common/top.jsp"%>

<div id="Catalog">
<form class="shippingForm" method="post">
    <table>
        <tr>
            <th colspan=2>Shipping Address</th>
        </tr>

        <tr>
            <td>First name:</td>
            <td><input type="text" name="shipToFirstName" value="${sessionScope.order.shipToFirstName}"/></td>
            </td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td><input type="text" name="shipToLastName" value="${sessionScope.order.shipToLastName}"/></td>
        </tr>
        <tr>
            <td>Address 1:</td>
            <td><input type="text" name="shipAddress1" value="${sessionScope.order.shipAddress1}"/></td>
        </tr>
        <tr>
            <td>Address 2:</td>
            <td><input type="text" name="shipAddress2" value="${sessionScope.order.shipAddress2}"/></td>
        </tr>
        <tr>
            <td>City:</td>
            <td><input type="text" name="shipCity" value="${sessionScope.order.shipCity}"/></td>
        </tr>
        <tr>
            <td>State:</td>
            <td><input type="text" name="shipState" value="${sessionScope.order.shipState}"/></td>
        </tr>
        <tr>
            <td>Zip:</td>
            <td><input type="text" name="shipZip" value="${sessionScope.order.shipZip}"/></td>
        </tr>
        <tr>
            <td>Country:</td>
            <td><input type="text" name="shipCountry" value="${sessionScope.order.shipCountry}"/></td>
        </tr>

    </table>
    <input type="submit" name="newOrder" value="Continue">


</form>
</div>

<%@ include file="../common/bottom.jsp"%>
