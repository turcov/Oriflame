<%-- 
    Document   : message
    Created on : Mar 3, 2015, 1:52:31 PM
    Author     : Turkov S
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="ckeditor" uri="http://ckeditor.com"%>
<%--<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />--%>
<c:set var="language" value="${locale}"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.iucosoft.oriflame.translates.text" />

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="listNews" type="java.util.List<com.iucosoft.oriflame.domain.News>"%>

<div class="container">
    <c:forEach var="news" items="${listNews}" varStatus="status">
        <div class="well" style="width: 70%">
            <form class="form-horizontal" action="editnewscms.html" method="POST">
                <input type="hidden" name="id" value="${news.id}"/>                        
                <input type="hidden" name="LOCALE" value="${news.locale}"/>                        
                <p style="color: red"> <b> News for "${news.locale.substring(0,2)}" pages</b></p>
                <div class="form-group">
                    <label for="datetime" class="col-sm-2 control-label"><fmt:message key="newsform.date"/> </label>
                    <div class="col-sm-10">
                        <input type="datetime" name="DATETIME"
                               value="<fmt:formatDate type="both" 
                                               dateStyle="short" 
                                               timeStyle="short" 
                                               value="${news.date}"/>" 
                               id="datetime" class="form-control" />  
                    </div>
                </div>
                <div class="form-group">
                    <label for="title" class="col-sm-2 control-label"><fmt:message key="newsform.title"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control"  name="TITLE" value="${news.title}" id="title"/>                        
                    </div>
                </div>
                <textarea name="INFO" rows="5" id="editor${status.count}">${news.info}
                </textarea>
                <br/>
                <button type="sumbit" class="btn btn-default" /><fmt:message key="newsform.savenews"  />  '${news.locale.substring(0,2)}'</button>
                <button type="button" onclick="location.href = 'newscms.html'" class="btn btn-default" /><fmt:message key="newsform.backtonews"/></button>
                <c:set var="ckeditorpath" value="${pageContext.servletContext.contextPath}/ckeditor/" />       
                <ckeditor:replace replace="editor${status.count}" basePath="${ckeditorpath}" />                    
            </form>
        </div>
    </c:forEach>
</div>
