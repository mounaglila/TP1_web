package listener;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

// Pas de @WebListener : l'objet est notifié tout seul quand il est mis dans la session
public class SessionUser implements HttpSessionBindingListener {

    private final String name;

    public SessionUser(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent e) {
        System.out.println("[SessionUser] valueBound : " + name
                + " ajouté à la session " + e.getSession().getId());
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent e) {
        System.out.println("[SessionUser] valueUnbound : " + name
                + " retiré de la session " + e.getSession().getId());
    }

    @Override
    public String toString() {
        return "SessionUser[" + name + "]";
    }
}