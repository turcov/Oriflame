<%-- 
    Document   : cmsmain
    Created on : Feb 28, 2015, 11:27:19 PM
    Author     : Serguei
--%>

<%@page contentType="text/html; charset=UTF-8"  language="java" pageEncoding="UTF-8" %>
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
        <title><fmt:message key="backend.pagenames.messages"/></title>
    </head>
    <body>
        <tags:mainmenucms menu="li1"/>

        <br/>
        <div class="container">
            <div style="width: 70%">

                <tags:messagestable messagesList="${messagesList}"/>
            </div>
        </div>
        <jsp:include page="../commons/footer.jsp"/>
    </body>
</html>
