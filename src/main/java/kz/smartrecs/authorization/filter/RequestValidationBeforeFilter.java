package kz.smartrecs.authorization.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class RequestValidationBeforeFilter implements Filter {

    public static final String AUTHORIZATION_SCHEME_BASIC = "Basic";
    private final Charset credentialsCharset = StandardCharsets.UTF_8;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String header = req.getHeader(AUTHORIZATION);
        if (header != null) {
            header = header.trim();
            if (StringUtils.startsWithIgnoreCase(header, AUTHORIZATION_SCHEME_BASIC)) {
                byte[] base64Token = header.substring(6).getBytes(credentialsCharset);
                byte[] decoded;
                try {
                    decoded = Base64.getDecoder().decode(base64Token);
                    final String token = new String(decoded, credentialsCharset);
                    int delim = token.indexOf(':');
                    if (delim == -1) {
                        throw new BadCredentialsException("Bad credentials! Invalid basic authentication token!");
                    }
                    final String email = token.substring(0, delim); // username
                    if (email.toLowerCase().contains("test")) {
                        res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    }
                } catch (IllegalArgumentException e) {
                    throw new BadCredentialsException("Failed to decode basic authentication token!");
                }
            }
        }
        chain.doFilter(request, response);
    }
}
