<%-- 
    Document   : index
    Created on : Feb 27, 2015, 12:36:12 PM
    Author     : Turkov S
--%>

<%@page contentType="text/html; charset=UTF-8"  language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
    <!--    <style>
            #header{
                color: brown;
                background: blanchedalmond;
                //background:#670000;
                padding:20px 0 20px 30px;            
                //border-style: double;
                //float:left;
                //line-height: 1;
            }
        </style>-->


    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Oriflame</title>
    </head>
    <body>

        <tags:mainmenu/>
        <%response.sendRedirect("about.html");%>
    </body>
</html>
