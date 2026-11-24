import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * /LogoutServlet              -> invalide la session
 * /LogoutServlet?mode=remove  -> supprime seulement les attributs user et sessionUser
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<html><head><meta charset='UTF-8'><title>Logout</title></head><body>");

        if (session == null) {
            out.println("<p>Aucune session active.</p>");
        } else if ("remove".equals(req.getParameter("mode"))) {
            session.removeAttribute("user");
            session.removeAttribute("sessionUser");
            out.println("<p>Attributs <b>user</b> et <b>sessionUser</b> supprimés (session conservée).</p>");
        } else {
            session.invalidate();
            out.println("<p>Session invalidée.</p>");
        }
        out.println("<p><a href='Form.html'>Accueil</a> | <a href='login.html'>Login</a> | "
                + "<a href='ListItems'>ListItems</a></p>");
        out.println("</body></html>");
    }
}