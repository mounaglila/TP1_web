package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class SessionAttributeListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent e) {
        System.out.println("[SessionAttr] AJOUTÉ : " + e.getName() + " = " + e.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent e) {
        // getValue() retourne l'ANCIENNE valeur
        System.out.println("[SessionAttr] REMPLACÉ : " + e.getName()
                + " (ancienne valeur = " + e.getValue() + ")");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent e) {
        System.out.println("[SessionAttr] SUPPRIMÉ : " + e.getName());
    }
}