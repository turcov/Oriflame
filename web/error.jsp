<%-- 
    Document   : error
    Created on : Feb 28, 2015, 10:29:23 PM
    Author     : Serguei
--%>

<%@page contentType="text/html; charset=UTF-8"  language="java" isErrorPage="true" pageEncoding="UTF-8"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags/"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERROR PAGE!</title>
    </head>
    <body onload="alert('${error}')">
        <tags:mainmenu/>
    </body>
</html>
