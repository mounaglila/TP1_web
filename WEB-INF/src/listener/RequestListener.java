package listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class RequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent e) {
        HttpServletRequest r = (HttpServletRequest) e.getServletRequest();
        System.out.println("[RequestListener] requestInitialized : " + r.getRequestURI());
    }

    @Override
    public void requestDestroyed(ServletRequestEvent e) {
        HttpServletRequest r = (HttpServletRequest) e.getServletRequest();
        System.out.println("[RequestListener] requestDestroyed : " + r.getRequestURI());
    }
}