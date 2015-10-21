<%-- 
    Document   : register
    Created on : Mar 1, 2015, 5:43:24 PM
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
        <title><fmt:message key="backend.pagenames.properties"/></title>
    </head>
    <body >
        <c:set var="key" value="${property.key}"/>
        <c:set var="propRU" value="${property.propertyRU}"/>
        <c:set var="propRO" value="${property.propertyRO}"/>
        <c:set var="propEN" value="${property.propertyEN}"/>
        <tags:mainmenucms menu="li4"/>
        <div class="container">
            <div style="width: 90%">
                ${text}
                <form class="form-horizontal" action="editpropertycms.html" method="POST" >
                    <div class="form-group">
                        <label for="key" class="col-sm-2 control-label">Key(Cheie)(Ключевое поле):</label>
                        <div class="col-sm-10">
                            <input type="text" name="key" value="${key}" class="form-control" id="key" readonly="true"/>    
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="propRU" class="col-sm-2 control-label">На русском:</label>
                        <div class="col-sm-10">
                            <input type="text" name="propRU" value="${propRU}" class="form-control" id="propRU"/>                        
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="propRO" class="col-sm-2 control-label">Romana:</label>
                        <div class="col-sm-10">
                            <input type="text" name="propRO" value="${propRO}" class="form-control" id="propRO"/>                        
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="propEN" class="col-sm-2 control-label">English:</label>
                        <div class="col-sm-10">
                            <input type="text" name="propEN" value="${propEN}" class="form-control" id="propEN"/>                        
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default" ><fmt:message key="backend.button.savetext"/></button>
                            <button type="button" onclick="location.href = 'propertiescms.html'" class="btn btn-default" /><fmt:message key="backend.pagenames.backtoproperties"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <jsp:include page="../commons/footer.jsp"/>
    </body>
</html>
