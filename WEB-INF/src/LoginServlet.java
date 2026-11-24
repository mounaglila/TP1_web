import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import listener.SessionUser;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");

        HttpSession session = req.getSession();          // création ou récupération
        session.setAttribute("user", username);
        session.setAttribute("sessionUser", new SessionUser(username));   // 1.4.3 extra

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<html><head><meta charset='UTF-8'><title>Login</title></head><body>");
        out.println("<h2>Connexion réussie</h2>");
        out.println("<p>Bienvenue " + username + ", vous êtes connecté.</p>");
        out.println("<p><a href='items.html'>Aller à items.html</a></p>");
        out.println("</body></html>");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}