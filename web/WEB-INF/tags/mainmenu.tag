<%-- 
    Document   : mainmenu
    Created on : Feb 28, 2015, 7:18:48 PM
    Author     : Serguei
--%>

<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css" >
<link rel="stylesheet" href="resources/bootstrap/css/bootstrap-theme.min.css" >


<script src="http://code.jquery.com/jquery.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />--%>
<c:set var="language" value="${locale}"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.iucosoft.oriflame.translates.text" />

<%@attribute name="menu"%>
<c:choose>
    <c:when test="${language=='ru'}">
        <c:set var="lang" value="RU"/>
    </c:when>
    <c:when test="${language=='ro'}">
        <c:set var="lang" value="RO"/>
    </c:when>
    <c:when test="${language=='en'}">
        <c:set var="lang" value="EN"/>
    </c:when>
    <c:otherwise>
        <c:set var="lang" value="DEFAULT_LANGUAGE"/>
    </c:otherwise>
</c:choose>

<script>
    window.onpageshow = function () {
        document.getElementById('${menu}').className = "active";
        //alert('${locale}');
    };
</script>

<div class="container" >
    <br/>
    <!--<img src="images/oriflame.png" class="img-responsive" alt="oriflame" />-->
    <div class="navbar navbar-inverse" >
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#b-menu-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse" id="b-menu-1">
            <ul class="nav navbar-nav" >
                <li id="li1" ><a href = "about.html"><fmt:message key="frontend.pagename.about"/></a></li>
                <li id="li2" ><a href = "news.html"><fmt:message key="frontend.pagename.news"/></a></li>
                <li id="li3" ><a href = "register.html"><fmt:message key="frontend.pagename.register"/></a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right" style="padding-right: 15px;padding-top: 7px">
                <li> <div class="dropdown" style="padding-right: 7px">
                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true" style="width: 60px">
                            ${lang}
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                            <li><a  href="?language=en">EN</a></li>
                            <li><a  href="?language=ru">RU</a></li>
                            <li><a  href="?language=ro">RO</a></li>
                        </ul>
                    </div></li>
                <li><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@getbootstrap" style="width: 90px"><fmt:message key="login.button.submit"/></button></li>
            </ul>
        </div>
    </div>
</div>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="exampleModalLabel"><fmt:message key="login.label.modal-title" /></h4>
            </div>
            <div class="modal-body">
                <form action="login.html" method="POST">
                    <div class="form-group">
                        <label for="username" class="control-label"><fmt:message key="login.label.username" />:</label>
                        <input type="text" class="form-control" id="username" name="USERNAME" >
                    </div>
                    <div class="form-group">
                        <label for="password" class="control-label"><fmt:message key="login.label.password" />:</label>
                        <input type="password" name="PASSWORD" class="form-control" id="password" value=""/>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="login.button.close"/></button>
                        <button type="submit" class="btn btn-primary"><fmt:message key="login.button.submit"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>