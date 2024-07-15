package com.flabum.squidzbackend.iam.application.internal.commandservices;

import com.flabum.squidzbackend.iam.domain.model.aggregates.User;
import com.flabum.squidzbackend.iam.domain.model.commands.SignUpCommand;
import com.flabum.squidzbackend.iam.domain.services.UserCommandService;
import com.flabum.squidzbackend.iam.infrastructure.hashing.bcrypt.BCryptHashingService;
import com.flabum.squidzbackend.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.flabum.squidzbackend.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptHashingService bcryptHashingService;

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



}
