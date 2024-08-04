package com.flabum.squidzbackend.iam.application.internal.queryservices;

import com.flabum.squidzbackend.iam.domain.model.aggregates.User;
import com.flabum.squidzbackend.iam.domain.model.queries.GetUserByJwtQuery;
import com.flabum.squidzbackend.iam.domain.model.valueobjects.EmailAddress;
import com.flabum.squidzbackend.iam.domain.services.UserQueryService;
import com.flabum.squidzbackend.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.flabum.squidzbackend.iam.infrastructure.token.jwts.services.TokenServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;
    private final TokenServiceImpl tokenService;

    @Override
    public Optional<User> execute(GetUserByJwtQuery query, HttpServletRequest request) {
        var jwt = TokenServiceImpl.getJwtFromCookie(request);
        var user = userRepository.findByEmail(new EmailAddress(tokenService.getUsernameFromToken(jwt))).orElseThrow(() -> new RuntimeException("User not found"));
        return Optional.of(user);
    }
}
