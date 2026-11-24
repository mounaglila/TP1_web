<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="person" type="lab.mvc.model.Person" scope="request"/>
<html>
<head><title>Page - né après 1990</title></head>
<body>
  <h2>Page dédiée : personne née en 1990 ou après</h2>
  <ul>
    <li>Prénom : <jsp:getProperty name="person" property="firstName"/></li>
    <li>Nom : <jsp:getProperty name="person" property="lastName"/></li>
    <li>Date de naissance : <jsp:getProperty name="person" property="birthDay"/></li>
    <li>Sexe : <jsp:getProperty name="person" property="sex"/></li>
  </ul>
  <p><a href="index.html">Retour</a></p>
</body>
</html>