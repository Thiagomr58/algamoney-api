package com.example.algamoney.api.token;

import org.apache.catalina.util.ParameterMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Thiago Rodrigues on 12/02/2020
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE) //Faz com que o filtro tenha uma prioridade muito alta pois preciso analizar essa requisição antes de todo mundo.
public class RefreshTokenCookiePreProcessorFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        if ("/oauth/token".equalsIgnoreCase(req.getRequestURI())
                && "refresh_token".equals(req.getParameter("grant_type"))
                && req.getCookies() != null){
            for (Cookie cookie : req.getCookies()){
                if (cookie.getName().equals("refreshToken")) {
                    String refreshToken = cookie.getValue();
                    req = new MyServeletRequestWrapper(req, refreshToken); // Substituo a req antiga a uma nova req contendo o refreshToken, pois uma vez criada, uma requisição não pode ser altereada.
                }
            }
        }

        chain.doFilter(req, response);
    }

    @Override
    public void destroy() {

    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    static class MyServeletRequestWrapper extends HttpServletRequestWrapper {

        private  String refreshToken;

        public MyServeletRequestWrapper(HttpServletRequest request, String refreshToken) {
            super(request);
            this.refreshToken = refreshToken;
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            ParameterMap<String, String[]> map = new ParameterMap<>(getRequest().getParameterMap());
            map.put("refresh_token", new String[] {refreshToken});
            map.setLocked(true);
            return map;
        }
    }


}
