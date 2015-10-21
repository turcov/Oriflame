<%-- 
    Document   : carousel
    Created on : Apr 27, 2015, 7:59:56 PM
    Author     : Serguei
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="imagesList" 
             type="java.util.List<com.iucosoft.oriflame.domain.ImagesPage>" %>


<style>
    .carousel-inner > .item > img,
    .carousel-inner > .item > a > img {
        width: 75%;
        margin:auto
    }
</style>
<div class="container" style="width:70%">
    <div id="myCarousel" class="carousel slide" data-ride="carousel" >
        <!-- Indicators -->
        <c:choose>
            <c:when test="${imagesList.size()>0}">
                <ol class="carousel-indicators">
                    <c:forEach items="${imagesList}" var="image" varStatus="count">
                        <li data-target="#myCarousel" data-slide-to="${count.index}"></li>
                        </c:forEach>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">

                    <c:forEach items="${imagesList}" var="image" varStatus="status">
                        <c:choose>
                            <c:when test="${status.index==0}">
                                <div class="item active">
                                </c:when>
                                <c:otherwise>
                                    <div class="item">
                                    </c:otherwise>
                                </c:choose>
                                <img src="${pageContext.servletContext.contextPath}/${image.directory}/${image.filename}">
                            </div>
                        </c:forEach>
                    </div>

                    <!-- Left and right controls -->
                    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>

                </c:when>
            </c:choose>
        </div>
    </div>


