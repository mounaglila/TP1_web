package lab.mvc.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lab.mvc.model.Person;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 1. Lecture des données du formulaire
        String firstName = request.getParameter("firstName");
        String lastName  = request.getParameter("lastName");
        String birthDay  = request.getParameter("birthDay");   // mm/dd/yyyy
        String sex       = request.getParameter("sex");

        // 2. Remplissage du bean (Model)
        Person person = new Person(firstName, lastName, birthDay, sex);

        // 3. Le bean est placé dans la requête
        request.setAttribute("person", person);

        // 4. Choix de la vue selon l'année de naissance
        int year;
        try {
            String d = birthDay.trim();
            year = Integer.parseInt(d.substring(d.lastIndexOf('/') + 1));
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Date invalide, format attendu : mm/dd/yyyy");
            return;
        }
        String page = (year < 1990) ? "/old.jsp" : "/young.jsp";

        // Avec include(), le type de contenu doit être défini avant
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.include(request, response);
    }
}