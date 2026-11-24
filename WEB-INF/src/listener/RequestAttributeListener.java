package listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class RequestAttributeListener implements ServletRequestAttributeListener {

    @Override
    public void attributeAdded(ServletRequestAttributeEvent e) {
        System.out.println("[ReqAttr] AJOUTÉ : " + e.getName() + " = " + e.getValue());
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent e) {
        System.out.println("[ReqAttr] REMPLACÉ : " + e.getName()
                + " (ancienne valeur = " + e.getValue() + ")");
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent e) {
        System.out.println("[ReqAttr] SUPPRIMÉ : " + e.getName());
    }
}