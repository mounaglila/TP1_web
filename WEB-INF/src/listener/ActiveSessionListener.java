package listener;

import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class ActiveSessionListener implements HttpSessionListener {

    private static final AtomicInteger active = new AtomicInteger(0);

    @Override
    public void sessionCreated(HttpSessionEvent e) {
        int n = active.incrementAndGet();
        e.getSession().getServletContext().setAttribute("activeSessions", n);
        System.out.println("[ActiveSessionListener] session créée " + e.getSession().getId()
                + " -> actives = " + n);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent e) {
        // Math.max évite un compteur négatif (sessions restaurées après un redémarrage de Tomcat)
        int n = active.updateAndGet(v -> Math.max(0, v - 1));
        e.getSession().getServletContext().setAttribute("activeSessions", n);
        System.out.println("[ActiveSessionListener] session détruite " + e.getSession().getId()
                + " -> actives = " + n);
    }
}