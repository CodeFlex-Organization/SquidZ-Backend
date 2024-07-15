package com.flabum.squidzbackend.iam.domain.services;

import com.flabum.squidzbackend.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {

    void execute(SeedRolesCommand command);

}
