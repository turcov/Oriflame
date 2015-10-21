<%-- 
    Document   : mainmenu
    Created on : Feb 28, 2015, 7:18:48 PM
    Author     : Serguei
--%>
<link rel="stylesheet" href="../resources/bootstrap/css/bootstrap.min.css" >
<link rel="stylesheet" href="../resources/bootstrap/css/bootstrap-theme.min.css" >

<script src="http://code.jquery.com/jquery.js"></script>
<script src="../resources/bootstrap/js/bootstrap.min.js"></script>

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
    window.onload = function () {
        document.getElementById('${menu}').className = "active";
    };
</script>

<div class="container">
    <img src="../images/oriflame.png" class="img-responsive" alt="oriflame"/>
    <div class="navbar navbar-inverse ">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#b-menu-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse" id="b-menu-1">        
            <ul class="nav navbar-nav">
                <li id="li1"><a href = "messagescms.html"><fmt:message key="backend.pagenames.messages"/></a></li>
                <li id="li2"><a href = "newscms.html"><fmt:message key="backend.pagenames.editnews"/></a></li>
                <li id="li3" class="dropdown" >
                    <a href="#" class="dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                        <fmt:message key="backend.dropdown.edittext" />
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                        <li><a href = "textcms.html?pagename=aboutpage"><fmt:message key="backend.pagenames.editaboutpage"/></a></li>
                        <li><a href = "textcms.html?pagename=newspage"><fmt:message key="backend.pagenames.editnewspage"/></a></li>
                        <li><a href = "textcms.html?pagename=registerpage"><fmt:message key="backend.pagenames.editregisterpage"/></a></li>
                    </ul>
                </li>
                <li id="li4"><a href = "propertiescms.html"><fmt:message key="backend.pagenames.properties"/></a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right" style="padding-right: 15px;padding-top: 7px">
                <li> <div class="dropdown" style="padding-right: 7px">
                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true" style="width: 50px">
                            ${lang}
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                            <li><a  href="?key=${property.key}&AMP;pagename=${pagename}&AMP;language=en">EN</a></li>
                            <li><a  href="?key=${property.key}&AMP;pagename=${pagename}&AMP;language=ru">RU</a></li>
                            <li><a  href="?key=${property.key}&AMP;pagename=${pagename}&AMP;language=ro">RO</a></li>
                        </ul>
                    </div></li>
                <li><button  type="button" onclick="location.href = 'logout.html'"  class="btn btn-primary " style="width: 80px"><fmt:message key="backend.button.logout"/></button></li>
            </ul>

        </div>
    </div>
</div>

