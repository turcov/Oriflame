<%-- 
    Document   : cmsmain
    Created on : Feb 28, 2015, 11:27:19 PM
    Author     : Serguei
--%>

<%@page contentType="text/html; charset=UTF-8"  language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <% response.sendRedirect("cms/messagescms.html");%>
        <jsp:include page="../commons/footer.jsp"/>
    </body>
</html>
