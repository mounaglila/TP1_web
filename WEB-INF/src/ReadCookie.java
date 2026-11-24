import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReadCookie extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body><h2>Cookies</h2><ul>");

        boolean found = false;
        Cookie[] cookies = request.getCookies();   // peut être null
        if (cookies != null) {
            for (Cookie c : cookies) {
                String n = c.getName();
                if (n.equals("firstName") || n.equals("lastName")
                        || n.equals("birthDay") || n.equals("sex")) {
                    out.println("<li>" + n + " = " + URLDecoder.decode(c.getValue(), "UTF-8") + "</li>");
                    found = true;
                }
            }
        }
        if (!found) out.println("<li>Aucun cookie trouvé (envoyez d'abord le formulaire)</li>");
        out.println("</ul><p><a href='Form.html'>Retour</a></p></body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
