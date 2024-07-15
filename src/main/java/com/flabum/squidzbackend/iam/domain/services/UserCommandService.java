package com.flabum.squidzbackend.iam.domain.services;


import com.flabum.squidzbackend.iam.domain.model.aggregates.User;
import com.flabum.squidzbackend.iam.domain.model.commands.SignUpCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> execute(SignUpCommand command);
}
