<%-- 
    Document   : cmsproperties
    Created on : Apr 28, 2015, 1:44:34 AM
    Author     : Serguei
--%>

<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="com.iucosoft.oriflame.domain.Property"%>
<%@page import="com.iucosoft.oriflame.domain.Property"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title><fmt:message key="backend.pagenames.properties"/></title>
    </head>
    <body>
        <tags:mainmenucms menu="li4"/>
        <div class="container" style="width: 80%">
            <table class="table table-bordered">
            <thead>
                <tr>
                    <th>NN</th>
                    <th>KEY</th>
                    <th>На русском</th>
                    <th>Limba romana</th>
                    <th>English</th>
                    <th><fmt:message key="newstable.edit"/></th>
                </tr>
            </thead>
            <tbody>
                <%
                    Map<String, Property> propsMap = (Map<String, Property>) request.getAttribute("propsMap");
                    Set<Map.Entry<String, Property>> entrySet = propsMap.entrySet();
                    int count = 1;
                    for (Map.Entry<String, Property> set : entrySet) {
                %>
                <tr>
                    <td><%=count%></td>
                    <td><%=set.getKey()%></td>
                    <td><%=set.getValue().getPropertyRU()%></td>
                    <td><%=set.getValue().getPropertyRO()%></td>
                    <td><%=set.getValue().getPropertyEN()%></td>
                    <td><a href="editpropertycms.html?key=<%=set.getKey()%>"/><fmt:message key="newstable.edit"/></td>
                </tr>
                <%
                        count++;
                    }
                %>
            </tbody>
        </table>
        </div>
        <jsp:include page="../commons/footer.jsp"/>
    </body>
</html>
