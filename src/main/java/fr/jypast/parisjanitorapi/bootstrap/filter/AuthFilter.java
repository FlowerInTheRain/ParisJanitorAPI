package fr.jypast.parisjanitorapi.bootstrap.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.regex.Pattern;


@Component
@Order(1)
public class AuthFilter implements Filter {

    private static final Pattern EXCLUDED_PATHS = Pattern.compile("^/parisjanitor-api/users(/.*)?$", Pattern.CASE_INSENSITIVE);

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse hsResponse = (HttpServletResponse) response;

        // Vérifiez si la requête est une requête POST vers "/users"
        boolean isExcluded = ("POST".equalsIgnoreCase(req.getMethod()) && EXCLUDED_PATHS.matcher(req.getRequestURI()).matches())
                || ("GET".equalsIgnoreCase(req.getMethod()) && req.getRequestURI().contains("verify"));

        // Vérifiez si la requête est une requête WebSocket
        boolean isWebSocket = req.getRequestURI().startsWith("/parisjanitor-api/ws");

        if (!isExcluded && !isWebSocket && !"OPTIONS".equalsIgnoreCase(req.getMethod())) {
            String authHeader = req.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                hsResponse.sendError(401);
                return;
            }
        }
        chain.doFilter(request, response);
    }
}