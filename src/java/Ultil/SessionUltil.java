
package Ultil;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author asus
 */
public class SessionUltil {

    private static SessionUltil sessionUltil = null;

    public void pushValue(HttpServletRequest request, String key, Object value) {
        request.getSession().setAttribute(key, value);
    }

    public Object getValue(HttpServletRequest request, String key) {
        return request.getSession().getAttribute(key);
    }

    public void removeValue(HttpServletRequest request, String key) {
        request.getSession().removeAttribute(key);
    }
    
    public static SessionUltil getInstance(){
        if(sessionUltil == null){
            sessionUltil = new SessionUltil();
        }
        return sessionUltil;
    }

}
