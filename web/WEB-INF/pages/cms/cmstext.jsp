<%-- 
    Document   : cmsmain
    Created on : Feb 28, 2015, 11:27:19 PM
    Author     : Serguei
--%>

<%@page import="com.iucosoft.oriflame.services.ImagesPageServiceDaoImpl"%>
<%@page import="com.iucosoft.oriflame.db.MyDataSource"%>
<%@page import="com.iucosoft.oriflame.domain.PageText"%>
<%@page import="com.iucosoft.oriflame.services.ImagesPageServiceIntf"%>
<%@page import="com.iucosoft.oriflame.domain.ImagesPage"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8"  language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ckeditor" uri="http://ckeditor.com"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />--%>
<c:set var="language" value="${locale}"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.iucosoft.oriflame.translates.text" />
<!DOCTYPE html>
<html>
    <head>
        <title><fmt:message key="backend.pagenames.cmstext"/></title>
        <script src="//cdn.ckeditor.com/4.4.7/full/ckeditor.js"></script>
        <script src="http://code.jquery.com/jquery.js"></script>        
        <script type='text/javascript'>

//            function isLoadedimage() {
//                element = document.getElementById("file");
//                isLoaded = true;
//                if (!element.files[0]) {
//                    isLoaded = false;
//                    alert('<fmt:message key="backend.textcms.imagenotloaded" />');
//                }
//                return isLoaded;
//            }
            function validation(componentId) {
                if (validateFileSize(document.getElementById(componentId), 1048576, "valid_msg", "Document size should be less than 1MB !") === false) {
                    return false;
                }
                if (validateFileExtension(document.getElementById(componentId), "valid_msg", "image files are only allowed!",
                        new Array("jpg", "jpeg", "gif", "png")) === false) {
                    return false;
                }

            }
            function validateFileExtension(component, msg_id, msg, extns) {
                var flag = 0;
                with (component) {
                    var ext = value.substring(value.lastIndexOf('.') + 1);
                    for (i = 0; i < extns.length; i++) {
                        if (ext === extns[i]) {
                            flag = 0;
                            break;
                        }
                        else {
                            flag = 1;
                        }
                    }
                    if (flag !== 0) {
                        alert('<fmt:message key="backend.textcms.imageformaterror" />');
//                        document.getElementById(msg_id).innerHTML = msg;
//                        component.value = "";
//                        component.style.backgroundColor = "#eab1b1";
//                        component.style.border = "thin solid #000000";
//                        component.focus();
                        return false;
                    }
                    else {
                        return true;
                    }
                }
            }
            function validateFileSize(component, maxSize, msg_id, msg) {
                if (navigator.appName === "Microsoft Internet Explorer")
                {
                    if (component.value) {
                        var oas = new ActiveXObject("Scripting.FileSystemObject");
                        var e = oas.getFile(component.value);
                        var size = e.size;
                    }
                }
                else {
                    if (component.files[0] !== undefined) {
                        size = component.files[0].size;
                    }
                }
                if (size === undefined) {
                    alert('<fmt:message key="backend.textcms.imagenotloaded" />');
                    return false;
                }
                if (size > maxSize) {
                    alert('<fmt:message key="backend.textcms.imagesizeoverloaded" />');
//                    document.getElementById(msg_id).innerHTML = msg;
//                    component.value = "";
//                    component.style.backgroundColor = "#eab1b1";
//                    component.style.border = "thin solid #000000";
//                    component.focus();
                    return false;
                }
                else {
                    return true;
                }
            }

        </script>
    </head>
    <body>
        <tags:mainmenucms menu="li3"/>
        <div class="container">
            <%
                List<PageText> textPages = (List) request.getAttribute("textPages");
                int status = 1;
                for (PageText pageText : textPages) {
                    request.setAttribute("pageText", pageText);
                    request.setAttribute("status", status);
            %>
            <%--<c:forEach var="pageText" items="${textPages}" varStatus="status">--%>
            <div class="well" style="width: 70% ; float:left" >
                <form action="textcms.html" method="POST">
                    <label style="color: red">Text for "${pageText.locale.substring(0,2)}" pages</label>
                    <input type="hidden" name="pagename" value="${pageText.pageName}"/>
                    <input type="hidden" name="locale" value="${pageText.locale}"/>
                    <br/>
                    <textarea id="editor${status}" name="pagetext" rows="4" cols="20">${pageText.text}
                    </textarea>
                    <br/>
                    <input type="submit" value=" <fmt:message key="backend.button.savetext"/>  '${pageText.locale.substring(0,2)}'"/>
                </form> 
                <c:set var="ckeditorpath" value="${pageContext.servletContext.contextPath}/ckeditor/" />       
                <ckeditor:replace replace="editor${status}" basePath="${ckeditorpath}" />                    
                <form  onsubmit="return validation('file${status}')" action ="imagescms.html" method="POST" enctype="multipart/form-data">
                    <label><fmt:message key="backend.caruselimages.select"/> '${pageText.locale.substring(0,2)}'</label>
                    <input type="hidden" name="pagename" value="${pageText.pageName}"/>
                    <input type="hidden" name="locale" value="${pageText.locale}"/>
                    <input type="file" name="file" id="file${status}"/>
                    <br/>
                    <input type="submit" value=" <fmt:message key="backend.button.uploadimage"/> "/>
                </form>

                <!--<div style="width:600px; float:left; border:#0480be solid 1px; padding-left: 20px; margin-left: 10px;">-->




                <%
                    MyDataSource dataSource = (MyDataSource) request.getServletContext().getAttribute("dataSource");
                    ImagesPageServiceIntf imagesPageService = ImagesPageServiceDaoImpl.getInstance(dataSource);
                    List<ImagesPage> imagesList = imagesPageService.findImagesByPageWithoutData(pageText);
                    //System.out.println(imagesList.size());
                    String imgPath = null;
                    for (ImagesPage image : imagesList) {
                        imgPath = request.getContextPath() + "/" + image.getDirectory() + "/" + image.getFilename();
                        //System.out.println("path is "+imgPath);

                %>       
                <div style=" float: left; margin:10px">      

                    <img src="<%=imgPath%>" alt="Image(Imagine(Картинка))"  width="100px" /> 
                    <a href='deleteimagecms?pagename=${pageText.pageName}&AMP;imgId=<%=image.getId()%>'  >delete</a>
                    </div>
                    <%
                        }
                    %>

                    <!--</div>-->     




                </div>
                <%
                        status++;
                    }
                %>
                <%--</c:forEach>--%>
            </div>
            <jsp:include page="../commons/footer.jsp"/>
    </body>
</html>
