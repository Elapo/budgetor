package be.zlz.budgetor.api.controller.filter;

import be.zlz.budgetor.api.exceptions.MissingAuthorizationTokenException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTFilter extends GenericFilterBean {

    private String jwtSecret;
    private Logger LOG;

    public JWTFilter(String secret) {
        super();
        this.jwtSecret = secret;
        LOG = Logger.getLogger(JWTFilter.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOG.debug("Filtering...");

        HttpServletResponse res = (HttpServletResponse) servletResponse;
        res.reset();

        String token = ((HttpServletRequest) servletRequest).getHeader("Authorization");
        try {
            if (token == null) throw new MissingAuthorizationTokenException();

            token = extractJwt(token);

            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(jwtSecret))
                    .withIssuer("budgetor")
                    .acceptExpiresAt(5*60)
                    .build();
            DecodedJWT jwt = verifier.verify(token);

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(jwt.getClaims(), null);
            SecurityContextHolder.getContext().setAuthentication(auth);

            filterChain.doFilter(servletRequest, servletResponse);
        } catch (MissingAuthorizationTokenException mate) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No Authorization token was found in your request.");
        } catch (JWTDecodeException ex) {
            logger.error("Failed to decode JWT", ex);

            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "The token you provided does not have the correct format.");
        } catch (JWTVerificationException jve) {
            logger.error("Unauthorized access", jve);

            //send 401
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You are not authorized to access this content.");
        }


    }

    private String extractJwt(String token) {
        if (token.contains(" ")) {
            token = token.split(" ")[1];
        }
        return token;
    }
}
