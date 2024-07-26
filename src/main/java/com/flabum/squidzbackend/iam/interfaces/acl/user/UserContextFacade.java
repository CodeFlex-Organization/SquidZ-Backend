package com.flabum.squidzbackend.iam.interfaces.acl.user;

import com.flabum.squidzbackend.iam.domain.model.aggregates.User;
import com.flabum.squidzbackend.iam.domain.model.valueobjects.EmailAddress;
import com.flabum.squidzbackend.iam.domain.services.UserCommandService;
import com.flabum.squidzbackend.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.flabum.squidzbackend.iam.infrastructure.token.jwts.services.TokenServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserContextFacade {
    private final UserCommandService userCommandService;

    private final UserRepository userRepository;

    private final TokenServiceImpl tokenService;


    public Optional<User> getUser(HttpServletRequest request) {
        var userAgent = request.getHeader(HttpHeaders.USER_AGENT);
        var jwt = "";
        if (userAgent != null && !userAgent.contains("Android") && !userAgent.contains("iPhone") && !userAgent.contains("iPad")) {
            jwt = TokenServiceImpl.getJwtFromCookie(request);
        }
        else{
            jwt = this.tokenService.getBearerTokenFrom(request);
        }

        var email = tokenService.getUsernameFromToken(jwt);

        return this.userRepository.findByEmail(new EmailAddress(email));
    }

}
