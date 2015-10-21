<%-- 
    Document   : cmsmain
    Created on : Feb 28, 2015, 11:27:19 PM
    Author     : Serguei
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.iucosoft.oriflame.domain.News"%>
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
        <title><fmt:message key="backend.pagenames.newspage"/></title>
    </head>
    <body>
        <tags:mainmenucms menu="li2"/>


        <div class="container">
            <div class="col-sm-10">
                <button type="button" onclick="location.href = 'addnewscms.html'" class="btn btn-default" ><fmt:message key="backend.button.addnews"/></button>
            </div>
            <br/>
            <br/>
            <div style="width: 80%">
                <c:choose>
                    <c:when test="${newsListRU==null || newsListRU.size() == 0}">
                        <h2><fmt:message key="backend.message.nohavenews"/> </h2>
                    </c:when>
                    <c:otherwise>
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th><fmt:message key="newstable.id"/></th>
                                    <th><fmt:message key="newstable.date"/></th>
                                    <th><fmt:message key="newstable.titleEN"/></th>
                                    <th><fmt:message key="newstable.titleMD"/></th>
                                    <th><fmt:message key="newstable.titleRU"/></th>
                                    <th><fmt:message key="newstable.edit"/></th>
                                    <th><fmt:message key="newstable.remove"/></th>
                                </tr>
                            </thead>
                            <tbody>

                                <%
                                    Integer size = (Integer) request.getAttribute("size");
                                    ArrayList<News> newsListEN = (ArrayList<News>) request.getAttribute("newsListEN");
                                    ArrayList<News> newsListMD = (ArrayList<News>) request.getAttribute("newsListMD");
                                    ArrayList<News> newsListRU = (ArrayList<News>) request.getAttribute("newsListRU");
                                    for (int i = 0; i < size; i++) {

                                %>
                                <tr>
                                    <td><c:out value="<%=i + 1%>"/></td>
                                    <td ><fmt:formatDate type="both" 
                                                    dateStyle="short" 
                                                    timeStyle="short" 
                                        value="<%=newsListRU.get(i).getDate()%>"/>
                                    </td>
                                    <td><c:out value="<%=newsListEN.get(i).getTitle()%>"/></td>
                                    <td><c:out value="<%=newsListMD.get(i).getTitle()%>"/></td>
                                    <td><c:out value="<%=newsListRU.get(i).getTitle()%>"/></td>
                                    <td><a href="editnewscms.html?id=<%=newsListRU.get(i).getId()%>"/><fmt:message key="newstable.edit"/></a></td>
                                    <td><a href="removenewscms.html?id=<%=newsListRU.get(i).getId()%>"><fmt:message key="newstable.remove"/></a></td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>





        <jsp:include page="../commons/footer.jsp"/>
    </body>
</html>
