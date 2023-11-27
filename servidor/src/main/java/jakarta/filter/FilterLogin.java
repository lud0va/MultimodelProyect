package jakarta.filter;


import config.ConstantServer;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = ConstantServer.FILTER_LOGIN,urlPatterns = {ConstantServer.API_PATH_LOGIN})
public class FilterLogin implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession(false);
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if (path.startsWith(ConstantServer.PATH_CUENTA_LOGINFILT) || (httpRequest.getMethod().equalsIgnoreCase(ConstantServer.POST) && path.startsWith(ConstantServer.API_USUARIOS_PATH))) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (session == null || session.getAttribute(ConstantServer.LOGIN) == null) {
            ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_FORBIDDEN, ConstantServer.FORBIDEN);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
