import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CountVisits extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int count = 0;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("visits")) {
                    try { count = Integer.parseInt(c.getValue()); } catch (NumberFormatException e) { count = 0; }
                }
            }
        }
        count++;
        Cookie visits = new Cookie("visits", String.valueOf(count));
        visits.setMaxAge(60 * 60 * 24 * 365);
        response.addCookie(visits);

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<html><body><h2>Visite n° " + count
                + "</h2><p><a href='Form.html'>Retour</a></p></body></html>");
    }
}
