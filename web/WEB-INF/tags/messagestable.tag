<%-- 
    Document   : messagestable
    Created on : Mar 3, 2015, 10:19:06 AM
    Author     : Turkov S
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />--%>
<c:set var="language" value="${locale}"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.iucosoft.oriflame.translates.text" />
<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="messagesList" 
             type="java.util.List<com.iucosoft.oriflame.domain.Message>" %>

    <c:choose>
        <c:when test="${messagesList==null || messagesList.size() == 0}">
            <h2><fmt:message key="backend.message.nohavemessages"/> </h2>
        </c:when>
        <c:otherwise>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th><fmt:message key="messagestable.id"/></th>
                        <th><fmt:message key="messagestable.date"/></th>
                        <th><fmt:message key="messagestable.name"/></th>
                        <th><fmt:message key="messagestable.email"/></th>
                        <th><fmt:message key="messagestable.phone"/></th>
                        <th><fmt:message key="messagestable.read"/></th>
                        <th><fmt:message key="messagestable.remove"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="message" items="${messagesList}" varStatus="status">
                        <c:choose>
                            <c:when test="${message.isReaded}">
                                <tr>
                                    <td><c:out value="${status.count}"/></td>
                                     <td ><fmt:formatDate type="both" 
                                                    dateStyle="short" 
                                                    timeStyle="short" 
                                                    value="${message.date}"/>
                                    </td>
                                    <td><c:out value="${message.name}"/></td>
                                    <td><c:out value="${message.email}"/></td>
                                    <td><c:out value="${message.phone}"/></td>
                                    <td><a href="readmessagecms.html?id=${message.id}"><fmt:message key="messagestable.read"/></a></td>
                                    <td><a href="removemessagecms.html?id=${message.id}"><fmt:message key="messagestable.remove"/></a></td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td ><b><c:out value="${status.count}"/></b></td>
                                    <td ><b><fmt:formatDate type="both" 
                                                    dateStyle="short" 
                                                    timeStyle="short" 
                                                    value="${message.date}"/></b>
                                    </td>
                                    <td ><b><c:out value="${message.name}"/></b></td>
                                    <td ><b><c:out value="${message.email}"/></b></td>
                                    <td ><b><c:out value="${message.phone}"/></b></td>
                                    <td><a href="readmessagecms.html?id=${message.id}"><fmt:message key="messagestable.read"/></a></td>
                                    <td><a href="removemessagecms.html?id=${message.id}"><fmt:message key="messagestable.remove"/></a></td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
