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
        <title><fmt:message key="frontend.pagename.register"/></title>
    </head>
        <script>
            window.onload = function () {
                resolvingerror('${warning}');
            };
            function validationEmptyFields() {
                element1 = document.getElementById("name");
                val1 = element1.value;
                returnfalse = false;
                if (val1 === "" || val1 === null) {
                    element1.setAttribute("style", "border-color :red");
                    returnfalse = true;
                } else {
                    element1.setAttribute("style", "border-color :light-gray");
                }
                element2 = document.getElementById("answer");
                val2 = element2.value;
                if (val2 === "" || val2 === null) {
                    element2.setAttribute("style", "border-color :red");
                    returnfalse = true;
                } else {
                    element2.setAttribute("style", "border-color :light-gray");
                }
                if (returnfalse) {
                    alert('<fmt:message key="registerform.alert.emptyfields" />');
                    return false;
                }
            }

            function resolvingerror(msg) {
                    //alert('<fmt:message key="registerform.alert.resolverror" />');
                if (msg !== "" && msg !== null) {
                    alert('<fmt:message key="registerform.alert.resolverror" />');
                }
            }
        </script>
    <body >
        <c:set var="name" value="${msgInstance.name}"/>
        <c:set var="email" value="${msgInstance.email}"/>
        <c:set var="phone" value="${msgInstance.phone}"/>
        <c:set var="message" value="${msgInstance.message}"/>
        <tags:mainmenu menu="li3"/>
        <div class="container">
            <tags:carousel imagesList="${imagesList}"/>
            <div style="width: 90%">
                ${text}
                <form class="form-horizontal" action="sendmessage.html" method="POST" >
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label"><fmt:message key="registerform.inputname"/> <span style="color: red">*</span>   </label>
                        <div class="col-sm-10">
                            <input type="text" name="NAME" value="${name}" class="form-control" id="name"/>    
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label"><fmt:message key="registerform.inputemail"/> </label>
                        <div class="col-sm-10">
                            <input type="email" name="EMAIL" value="${email}" class="form-control" id="email"/>                        
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="phone" class="col-sm-2 control-label"><fmt:message key="registerform.inputphone"/>   </label>
                        <div class="col-sm-10">
                            <input type="tel" name="PHONE" value="${phone}" class="form-control" id="phone"/>    
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="message" class="col-sm-2 control-label"><fmt:message key="registerform.typemessage"/>    </label>
                        <div class="col-sm-10">
                            <textarea name="MESSAGE" rows="3" class="form-control" id="message">${message}
                            </textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="answer" class="col-sm-2 control-label"> <fmt:message key="registerform.resolve"/> ${question}<span style="color: red">*</span> </label>
                        <div class="col-sm-10">
                            <input ${warning}  type="number" name="ANSWER" value="" class="form-control" id="answer" />    
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default" onclick="return validationEmptyFields()" ><fmt:message key="registerform.sendmessage"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <jsp:include page="commons/footer.jsp"/>
    </body>
</html>
