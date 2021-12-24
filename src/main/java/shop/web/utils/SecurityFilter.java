package shop.web.utils;

import shop.service.SecurityService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SecurityFilter implements Filter {
    final private SecurityService securityService;

    private final List<String> allows = List.of("/css", "/login", "/logout");

    public SecurityFilter(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String requestURI = ((HttpServletRequest) request).getRequestURI();
        for (String allow : allows) {
            requestURI.startsWith(allow);
            System.out.println("Before");
            chain.doFilter(request, response);
            System.out.println("After");
            return;
        }

//        (securityService.isLoggedIn(httpServletRequest.getCookies()))
//                ? chain.doFilter(request, response)
//                : httpServletResponse.sendRedirect("/login");

//        if (!HttpServletRequest.getRequestURI().startsWith("/css")) {
//            httpServletResponse.sendRedirect("/login");
//        } else {
//            chain.doFilter(request, response);
//        }

        System.out.println("SecurityFilter");
    }

    @Override
    public void destroy() {

    }


    @Override
    public void init(FilterConfig filterConfig) {

    }
}
