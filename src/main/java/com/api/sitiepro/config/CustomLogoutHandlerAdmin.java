package com.api.sitiepro.config;

import com.api.sitiepro.entity.TokenAdmin;
import com.api.sitiepro.repository.TokenRepositoryAdmin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLogoutHandlerAdmin implements LogoutHandler {

    private final TokenRepositoryAdmin tokenRepositoryAdmin;

    public CustomLogoutHandlerAdmin(TokenRepositoryAdmin tokenRepositoryAdmin) {
        this.tokenRepositoryAdmin = tokenRepositoryAdmin;
    }

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || authHeader.startsWith("Bearer ")) {
            return;
        }

        String token = authHeader.substring(7);

        TokenAdmin storedTokenAdmin = tokenRepositoryAdmin.findByTokenAdmin(token).orElse(null);

        if(token != null) {
            storedTokenAdmin.setLoggedOutAdmin(true);
            tokenRepositoryAdmin.save(storedTokenAdmin);
        }
    }
}
