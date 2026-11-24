import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Mapping declaré dans web.xml : /FormServlet et /Form_Servlet
public class FormServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String firstName = request.getParameter("firstName");
        String lastName  = request.getParameter("lastName");
        String birthDay  = request.getParameter("birthDay");
        String sex       = request.getParameter("sex");
        if (firstName == null) firstName = "";
        if (lastName == null)  lastName = "";
        if (birthDay == null)  birthDay = "";
        if (sex == null)       sex = "";

        // 1.2 : création des 4 cookies (AVANT d'écrire dans la réponse)
        String enc = "UTF-8";
        Cookie[] cookies = {
            new Cookie("firstName", URLEncoder.encode(firstName, enc)),
            new Cookie("lastName",  URLEncoder.encode(lastName, enc)),
            new Cookie("birthDay",  URLEncoder.encode(birthDay, enc)),
            new Cookie("sex",       URLEncoder.encode(sex, enc))
        };
        for (Cookie c : cookies) {
            c.setMaxAge(60 * 60 * 24);   // 1 jour
            response.addCookie(c);
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<table border='1'>");
        out.println("<tr><th>Champ</th><th>Valeur</th></tr>");
        out.println("<tr><td>Prénom</td><td>" + firstName + "</td></tr>");
        out.println("<tr><td>Nom</td><td>" + lastName + "</td></tr>");
        out.println("<tr><td>Date de naissance</td><td>" + birthDay + "</td></tr>");
        out.println("<tr><td>Sexe</td><td>" + sex + "</td></tr>");
        out.println("</table>");
        out.println("<p><a href='ReadCookie'>Lire les cookies</a> | <a href='Form.html'>Retour</a></p>");
        out.println("</body></html>");
    }
}
