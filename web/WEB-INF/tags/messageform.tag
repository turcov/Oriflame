<%-- 
    Document   : message
    Created on : Mar 3, 2015, 1:52:31 PM
    Author     : Turkov S
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />--%>
<c:set var="language" value="${locale}"/><fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.iucosoft.oriflame.translates.text" />


<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message" type="com.iucosoft.oriflame.domain.Message"%>

        <div class="container">
            <form class="form-horizontal" >
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label"><fmt:message key="messagesform.from"/></label>
                    <div class="col-sm-10">
                        <input type="text" name="NAME" value="${message.name}" class="form-control" id="name" readonly="true"/>    
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="col-sm-2 control-label"><fmt:message key="messagesform.email"/>  </label>
                    <div class="col-sm-10">
                        <input type="email" name="EMAIL" value="${message.email}" class="form-control" id="email" readonly="true"/>                        
                    </div>
                </div>
                <div class="form-group">
                    <label for="phone" class="col-sm-2 control-label"><fmt:message key="messagesform.phone"/> </label>
                    <div class="col-sm-10">
                        <input type="tel" name="PHONE" value="${message.phone}" class="form-control" id="phone" readonly="true"/>    
                    </div>
                </div>
                <div class="form-group">
                    <label for="message" class="col-sm-2 control-label"><fmt:message key="messagesform.message"/>  </label>
                    <div class="col-sm-10">
                        <textarea name="MESSAGE" rows="5" class="form-control" id="message" readonly="true">${message.message}
                        </textarea>
                    </div>
                </div>
            </form>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="button" onclick="location.href='messagescms.html'" class="btn btn-default" /><fmt:message key="messagesform.backtomessages"/></button>
                        <button type="button" onclick="location.href='removemessagecms.html?id=${message.id}'" class="btn btn-default" ><fmt:message key="messagesform.removemessage"/></button>
                    </div>
                </div>                    
        </div>
