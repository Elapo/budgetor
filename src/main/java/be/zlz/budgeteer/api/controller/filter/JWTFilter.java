package be.zlz.budgeteer.api.controller.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filtering");
        String token = ((HttpServletRequest) servletRequest).getHeader("Authorization");
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256("secret"))
                    .withIssuer("budgeteer")
                    .build();
            /*JWT jwt = verifier.verify(token);*/
            if (true) {
                HttpServletResponse res = (HttpServletResponse) servletResponse;
                res.reset();
                res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You are not authorized to access this content.");
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (JWTVerificationException jve) {
            jve.printStackTrace();
        }
    }
}
