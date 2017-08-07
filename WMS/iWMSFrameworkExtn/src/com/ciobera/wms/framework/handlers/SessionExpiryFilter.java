package com.ciobera.wms.framework.handlers;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionExpiryFilter implements Filter {

    /*http://www.ateam-oracle.com/tips-on-dealing-with-the-session-time-out-popup/*/
    private FilterConfig _filterConfig = null;

    public SessionExpiryFilter() {
        super();
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        _filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException,
                                                         ServletException {
        String requestedSession =
            ((HttpServletRequest)servletRequest).getRequestedSessionId();
        String currentWebSession =
            ((HttpServletRequest)servletRequest).getSession().getId();
        String requestURI =
            ((HttpServletRequest)servletRequest).getRequestURI();
        boolean sessionOk =
            currentWebSession.equalsIgnoreCase(requestedSession);
        System.out.println("currentWebSession == requestedSession? : " +
                           sessionOk);
        if (!sessionOk && requestedSession != null) {
            ((HttpServletResponse)servletResponse).sendRedirect(requestURI);
            System.out.println("redirecting to : " + requestURI);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
            System.out.println("session is OK");
        }
    }

    public void destroy() {
        _filterConfig = null;
    }
}
