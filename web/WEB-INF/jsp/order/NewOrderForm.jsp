<%@ include file="../common/top.jsp"%>

<div id="Catalog">
    <form method="post" action="confirmOrderForm">
    <table>
        <tr>
            <th colspan=2>Payment Details</th>
        </tr>
        <tr>
            <td>Card Type:</td>
            <td><select>
                <option value="Visa" selected="selected">Visa</option>
                <option value="MasterCard">MasterCard</option>
                <option value="American Express">American Express</option>
            </select>
        </tr>
        <tr>
            <td>Card Number:</td>
            <td>
                <input type="text" name="creditCard" value="999999999999">
            </td>
        </tr>
        <tr>
            <td>Expiry Date (MM/YYYY):</td>
            <td><stripes:text name="order.expiryDate" />
                <input type="text" name="expiryDate" value="12/12">
            </td>
        </tr>
        <tr>
            <th colspan=2>Billing Address</th>
        </tr>

        <tr>
            <td>First name:</td>
            <td>
                <input type="text" name="billToFirstName" value="${sessionScope.order.billToFirstName}">
            </td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td>
                <input type="text" name="billToLastName" value="${sessionScope.order.billToLastName}">
            </td>
        </tr>
        <tr>
            <td>Address 1:</td>
            <td>
                <input type="text" name="billAddress1" value="${sessionScope.order.billAddress1}">
            </td>
        </tr>
        <tr>
            <td>Address 2:</td>
            <td>
                <input type="text" name="billAddress2" value="${sessionScope.order.billAddress2}">
            </td>
        </tr>
        <tr>
            <td>City:</td>
            <td>
                <input type="text" name="billCity" value="${sessionScope.order.billCity}">
            </td>
        </tr>
        <tr>
            <td>State:</td>
            <td>
                <input type="text" name="billState" value="${sessionScope.order.billState}">
            </td>
        </tr>
        <tr>
            <td>Zip:</td>
            <td>
                <input type="text" name="billZip" value="${sessionScope.order.billZip}">
            </td>
        </tr>
        <tr>
            <td>Country:</td>
            <td>
                <input type="text" name="billCountry" value="${sessionScope.order.billCountry}">
            </td>
        </tr>

        <tr>
            <td colspan=2>
                <input id="shippingAddressRequired" type="checkbox" name="shippingAddressRequired" checked="checked">
                Ship to different address...</td>
        </tr>

            <tr class="Shipping">
                <th colspan=2>Shipping Address</th>
            </tr>

            <tr class="Shipping">
                <td>First name:</td>
                <td><input type="text" name="shipToFirstName" value="${sessionScope.order.shipToFirstName}"/></td>
            </tr>
            <tr class="Shipping">
                <td>Last name:</td>
                <td><input type="text" name="shipToLastName" value="${sessionScope.order.shipToLastName}"/></td>
            </tr>
            <tr class="Shipping">
                <td>Address 1:</td>
                <td><input type="text" name="shipAddress1" value="${sessionScope.order.shipAddress1}"/></td>
            </tr>
            <tr class="Shipping">
                <td>Address 2:</td>
                <td><input type="text" name="shipAddress2" value="${sessionScope.order.shipAddress2}"/></td>
            </tr>
            <tr class="Shipping">
                <td>City:</td>
                <td><input type="text" name="shipCity" value="${sessionScope.order.shipCity}"/></td>
            </tr>
            <tr class="Shipping">
                <td>State:</td>
                <td><input type="text" name="shipState" value="${sessionScope.order.shipState}"/></td>
            </tr>
            <tr class="Shipping">
                <td>Zip:</td>
                <td><input type="text" name="shipZip" value="${sessionScope.order.shipZip}"/></td>
            </tr>
            <tr class="Shipping">
                <td>Country:</td>
                <td><input type="text" name="shipCountry" value="${sessionScope.order.shipCountry}"/></td>
            </tr>
        </table>
        <input type="submit" name="newOrder" value="Continue">

</form>
</div>

<%@ include file="../common/bottom.jsp"%>