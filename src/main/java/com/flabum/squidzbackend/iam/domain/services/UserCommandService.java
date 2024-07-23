package com.flabum.squidzbackend.iam.domain.services;


import com.flabum.squidzbackend.iam.domain.model.aggregates.User;
import com.flabum.squidzbackend.iam.domain.model.commands.SignInCommand;
import com.flabum.squidzbackend.iam.domain.model.commands.SignUpCommand;
import com.flabum.squidzbackend.iam.domain.model.commands.UpdatePasswordCommand;
import com.flabum.squidzbackend.iam.domain.model.commands.UpdateUserDataCommand;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> execute(SignUpCommand command);

    Optional<ImmutablePair<User, String>> execute(SignInCommand command);

    boolean execute (UpdatePasswordCommand command, HttpServletRequest request);

    boolean execute (UpdateUserDataCommand command, HttpServletRequest request);

}
