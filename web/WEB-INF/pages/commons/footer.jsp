<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="date" value="<%=new java.util.Date()%>"/>
<footer class="footer">
    <div class="container">
        <p><a href="http://iucosoft.com">&copy; IUCOSOFT <f:formatDate pattern="yyyy" value="${date}"/></a></p>
    </div>
</footer>
