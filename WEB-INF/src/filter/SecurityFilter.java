package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Pas d'annotation : enregistré dans web.xml
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig cfg) throws ServletException {
        System.out.println("[SecurityFilter] init()");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        System.out.println("[SecurityFilter] AVANT : " + request.getRequestURI());

        HttpSession session = request.getSession(false);     // ne crée pas de session
        boolean connected = (session != null && session.getAttribute("user") != null);

        if (connected) {
            System.out.println("[SecurityFilter] accès autorisé");
            chain.doFilter(req, res);
            System.out.println("[SecurityFilter] APRES (accès autorisé)");
        } else {
            System.out.println("[SecurityFilter] accès refusé -> redirection vers login.html");
            response.sendRedirect(request.getContextPath() + "/login.html");
            System.out.println("[SecurityFilter] APRES (chain.doFilter NON appelé)");
        }
    }

    @Override
    public void destroy() {
        System.out.println("[SecurityFilter] destroy()");
    }
}