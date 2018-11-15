<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Error</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/tacit-css-1.3.4.min.css"/>"/>
</head>
<body>
<h2>${message}</h2>
<br>
<a href="javascript:history.back()">Back</a>
</body>
</html>
