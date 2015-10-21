<%-- 
    Document   : error
    Created on : Feb 28, 2015, 10:29:23 PM
    Author     : Serguei
--%>

<%@page contentType="text/html; charset=UTF-8"  language="java" isErrorPage="true" pageEncoding="UTF-8"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.iucosoft.oriflame.translates.text" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERROR PAGE!</title>
    </head>
    <body onload="alert('<fmt:message key="alert.sessionexpired"/>')">
        <tags:mainmenu/>
    </body>
</html>
