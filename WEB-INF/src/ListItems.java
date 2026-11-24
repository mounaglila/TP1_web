import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListItems extends HttpServlet {

    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        ArrayList<String> items = (ArrayList<String>) session.getAttribute("items");
        if (items == null) {
            items = new ArrayList<>();
            session.setAttribute("items", items);
        }
        String newItem = request.getParameter("newItem");
        if (newItem != null && !newItem.trim().isEmpty()) {
            items.add(newItem.trim());
        }
        display(items, response);
    }

    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<String> items = (ArrayList<String>) session.getAttribute("items");
        display(items == null ? new ArrayList<String>() : items, response);
    }

    private void display(ArrayList<String> items, HttpServletResponse response) throws IOException {
        // Extra : nombre de saisies de chaque item
        Map<String, Integer> counts = new LinkedHashMap<>();
        for (String s : items) counts.merge(s, 1, Integer::sum);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body><h2>Items</h2><ul>");
        for (Map.Entry<String, Integer> e : counts.entrySet()) {
            out.println("<li>" + e.getKey() + " (saisi " + e.getValue() + " fois)</li>");
        }
        out.println("</ul><a href='items.html'>Ajouter un item</a></body></html>");
    }
}
