<%-- 
    Document   : congrajulations
    Created on : Mar 3, 2015, 1:27:18 PM
    Author     : Turkov S
--%>


<%@page contentType="text/html; charset=UTF-8"  language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />--%>
<c:set var="language" value="${locale}"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.iucosoft.oriflame.translates.text" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Congrajulations!!!</title>
    </head>
    <body>
        <tags:mainmenu/>
        <div class="container">
            <div style="width: 70%">
                <h2><fmt:message key="congrajulations1"/></h2>
                <h2><fmt:message key="congrajulations2"/></h2>
            </div>
        </div>
        <jsp:include page="commons/footer.jsp"/>
    </body>
</html>