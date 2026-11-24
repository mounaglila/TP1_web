import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowSession extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        // Message d'accueil + compteur de visites
        Integer visits = (Integer) session.getAttribute("visits");
        String message;
        if (visits == null) {
            message = "Welcome on my site";
            visits = 1;
        } else {
            message = "Welcome back";
            visits = visits + 1;
        }
        session.setAttribute("visits", visits);

        // 1.4.2 : nombre de sessions actives (rempli par ActiveSessionListener)
        ServletContext ctx = getServletContext();
        Object active = ctx.getAttribute("activeSessions");

        out.println("<html><body>");
        out.println("<h2>" + message + "</h2>");
        out.println("<p>Nombre de visites : " + visits + "</p>");
        out.println("<p>Sessions actives : " + (active == null ? "?" : active) + "</p>");

        // Extra : nom complet + date de naissance
        String fullName = (String) session.getAttribute("fullName");
        String birth = (String) session.getAttribute("birth");
        if (fullName == null || birth == null) {
            out.println("<form method='POST' action='ShowSession'>");
            out.println("Nom complet : <input name='fullName'><br>");
            out.println("Date de naissance (mm/dd/yyyy) : <input name='birth'><br>");
            out.println("<input type='submit'></form>");
        } else {
            try {
                LocalDate b = LocalDate.parse(birth, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                LocalDate today = LocalDate.now();
                LocalDate next = b.withYear(today.getYear());
                if (next.isBefore(today)) next = next.plusYears(1);
                long days = ChronoUnit.DAYS.between(today, next);
                out.println("<p>Hi, " + fullName + ". There are " + days + " days to your birthday.</p>");
            } catch (DateTimeParseException e) {
                out.println("<p>Date invalide, format attendu : mm/dd/yyyy.</p>");
                session.removeAttribute("birth");
            }
        }
        out.println("<p><a href='ShowSession'>Recharger</a> | <a href='Form.html'>Retour</a></p>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        session.setAttribute("fullName", request.getParameter("fullName"));
        session.setAttribute("birth", request.getParameter("birth"));
        response.sendRedirect("ShowSession");
    }
}
