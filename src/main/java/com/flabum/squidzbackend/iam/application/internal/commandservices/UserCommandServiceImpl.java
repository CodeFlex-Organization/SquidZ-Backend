package com.flabum.squidzbackend.iam.application.internal.commandservices;

import com.flabum.squidzbackend.iam.domain.model.aggregates.User;
import com.flabum.squidzbackend.iam.domain.model.commands.SignInCommand;
import com.flabum.squidzbackend.iam.domain.model.commands.SignUpCommand;
import com.flabum.squidzbackend.iam.domain.model.commands.UpdatePasswordCommand;
import com.flabum.squidzbackend.iam.domain.model.valueobjects.EmailAddress;
import com.flabum.squidzbackend.iam.domain.services.UserCommandService;
import com.flabum.squidzbackend.iam.infrastructure.hashing.bcrypt.BCryptHashingService;
import com.flabum.squidzbackend.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.flabum.squidzbackend.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.flabum.squidzbackend.iam.infrastructure.token.jwts.TokenService;
import com.flabum.squidzbackend.iam.infrastructure.token.jwts.services.TokenServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptHashingService bcryptHashingService;

    private final TokenService tokenService;

    @Override
    public Optional<User> execute(SignUpCommand command) {
        if (userRepository.existsByEmail(command.emailAddress())) {
            throw new RuntimeException("User already exists with this email address");
        }
        var roles = command.roles().stream().map(role -> {
           return roleRepository.findByName(role.getName()).orElseThrow(()-> new RuntimeException("Role name not found"));
        }).toList();
        var user = new User(command.name().firstName(), command.name().lastName(), bcryptHashingService.encode(command.password()),
                roles, command.phoneNumber().countryCode(), command.phoneNumber().number(), command.emailAddress().address());
        userRepository.save(user);
        return Optional.of(user);
    }

    @Override
    public Optional<ImmutablePair<User, String>> execute(SignInCommand command) {
        var user = userRepository.findByEmail(command.email());
        if (user.isEmpty()){
            throw new RuntimeException("User not found");
        }
        if (!bcryptHashingService.matches(command.password(), user.get().getPassword())){
            throw new RuntimeException("Invalid password");
        }
        var token = tokenService.generateToken(user.get().getEmail().address());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }

    @Override
    public boolean execute(UpdatePasswordCommand command, HttpServletRequest request) {
        var email = tokenService.getUsernameFromToken(TokenServiceImpl.getJwtFromCookie(request));
        var user = userRepository.findByEmail(new EmailAddress(email));
        if (user.isEmpty()){
            throw new RuntimeException("User not found");
        }
        if (!bcryptHashingService.matches(command.currentPassword(), user.get().getPassword())){
            throw new RuntimeException("Invalid password");
        }
        user.get().setPassword(bcryptHashingService.encode(command.newPassword()));
        userRepository.save(user.get());
        return true;
    }


}
