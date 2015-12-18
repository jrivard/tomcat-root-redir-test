import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        out("--begin request");

        HttpSession httpSession = ((HttpServletRequest)request).getSession();
        String reqValue = request.getParameter("value");
        if (reqValue == null) {
            out("incoming request does not have value parameter");
            String randomValue = UUID.randomUUID().toString();
            httpSession.setAttribute("value", randomValue);
            out("set random value in session to " + randomValue);
            String redirectUri = ((HttpServletRequest) request).getRequestURL().toString();
            redirectUri += redirectUri.contains("?") ? "&" : "?" + "value=" + randomValue;
            ((HttpServletResponse)response).sendRedirect(redirectUri);
            out("sent redirect to " + redirectUri);
            return;
        }
        out("incoming request has value parameter = " + reqValue);
        String sessionValue = (String)httpSession.getAttribute("value");
        out("incoming request has session value = " + sessionValue);

        request.setAttribute("requestValue", reqValue);
        request.setAttribute("sessionValue", sessionValue);
        request.setAttribute("match", reqValue.equals(sessionValue));
        out("match status = " + reqValue.equals(sessionValue));

        chain.doFilter(request,response);

    }

    @Override
    public void destroy() {

    }

    static void out(CharSequence output) {
        System.out.println(output);
    }
}
