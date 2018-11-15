<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta charset="utf-8">
    <title>XMLController</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/tacit-css-1.3.4.min.css"/>"/>
</head>
<body>
<c:url value="/xmlContoller" var="xmlControllerURL"/>
<h1><b>XMLController</b></h1>
<form action="${xmlControllerURL}" method="post">
    <table>
        <tr>
            <td><b>test parameter:</b></td>
            <td><input type="text" name="test"/></td>
        </tr>
        <tr>
            <td colspan="2" align="right"><input type="submit" value="Test"></td>
        </tr>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </table>
</form>

<b>Request received: </b> ${xmlModelObject}
<br>
<a href="javascript:history.back()">Back</a>
</body>
</html>
