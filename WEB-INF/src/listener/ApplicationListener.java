package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent e) {
        System.out.println("[ApplicationListener] Application démarrée");
        e.getServletContext().setAttribute("appName", "MP2 Web Application");
    }

    @Override
    public void contextDestroyed(ServletContextEvent e) {
        System.out.println("[ApplicationListener] Application arrêtée");
    }
}