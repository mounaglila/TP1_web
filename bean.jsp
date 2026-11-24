<%@ page contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="person" class="isep.lab1.bean.Person" scope="request"/>
<jsp:setProperty name="person" property="*"/>
<html>
<head><title>Bean Person</title></head>
<body>
  <h2>Informations (via JavaBean)</h2>
  <table border="1" cellpadding="5">
    <tr><th>Prénom</th><td><jsp:getProperty name="person" property="firstName"/></td></tr>
    <tr><th>Nom</th><td><jsp:getProperty name="person" property="lastName"/></td></tr>
    <tr><th>Date de naissance</th><td><jsp:getProperty name="person" property="birthDay"/></td></tr>
    <tr><th>Sexe</th><td><jsp:getProperty name="person" property="sex"/></td></tr>
  </table>
  <p><a href="form-jsp.html">Retour</a></p>
</body>
</html>