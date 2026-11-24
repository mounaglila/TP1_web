package listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationAttributeListener implements ServletContextAttributeListener {

    @Override
    public void attributeAdded(ServletContextAttributeEvent e) {
        System.out.println("[AppAttr] AJOUTÉ : " + e.getName() + " = " + e.getValue());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent e) {
        System.out.println("[AppAttr] REMPLACÉ : " + e.getName()
                + " (ancienne valeur = " + e.getValue() + ")");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent e) {
        System.out.println("[AppAttr] SUPPRIMÉ : " + e.getName());
    }
}