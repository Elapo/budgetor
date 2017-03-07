package be.zlz.budgeteer.api.controller.filter;

import be.zlz.budgeteer.api.exceptions.MissingAuthorizationTokenException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
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

        HttpServletResponse res = (HttpServletResponse) servletResponse;
        res.reset();

        String token = ((HttpServletRequest) servletRequest).getHeader("Authorization");

        try {
            if(token == null) throw new MissingAuthorizationTokenException();
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256("secret"))
                    .withIssuer("budgeteer")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (JWTVerificationException jve) {
            jve.printStackTrace();

            //send 401
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You are not authorized to access this content.");
        } catch (MissingAuthorizationTokenException mate){
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No Authorization token was found in your request.");
        }

    }
}
