<%-- 
    Document   : news
    Created on : Mar 1, 2015, 5:42:49 PM
    Author     : Serguei
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
        <title><fmt:message key="frontend.pagename.news"/></title>
    </head>
    <body>
        <tags:mainmenu menu="li2"/>
        <div class="container">
            <div class="blog-header">
                ${text}
            </div>
            <tags:carousel imagesList="${imagesList}"/>
            <br/>
            <div class="col-sm-8 blog-main">
                <c:forEach var="news" items="${newsList}" varStatus="status">
                    <div class="well" id="heading${status.count}">
                        <h5><b><fmt:formatDate type="both" 
                                        dateStyle="short" 
                                        timeStyle="short" 
                                        value="${news.date}"/></b>  ${news.title}</h5>
                        <p>${news.info}</p>
                    </div>
                </c:forEach>
            </div>
            <div class="col-sm-3 col-sm-offset-1 blog-sidebar">
                <div class="sidebar-module">
                    <ol class="list-unstyled">
                        <c:forEach var="news" items="${newsList}" varStatus="status">
                            <li><a href="#heading${status.count}"><fmt:formatDate type="both" 
                                                             dateStyle="short" 
                                                             timeStyle="short" 
                                                             value="${news.date}"/> 
                                    <br/>
                                    ${news.title}</a></li>
                            <br/>
                        </c:forEach>
                    </ol>
                </div>

            </div>
        </div>
        <jsp:include page="commons/footer.jsp"/>
    </body>
</html>
