<%@ page contentType="text/html;charset=UTF-8" import="java.util.Date" %>
<html>
<head><title>Scriptlet JSP</title></head>
<body>
<%
    out.println("<p>Heure actuelle : " + new Date() + "</p>");
    out.println("<p>request.getRemoteHost() (hôte du client) : " + request.getRemoteHost() + "</p>");
    out.println("<p>Adresse IP du serveur (getLocalAddr) : " + request.getLocalAddr() + "</p>");
%>
</body>
</html>