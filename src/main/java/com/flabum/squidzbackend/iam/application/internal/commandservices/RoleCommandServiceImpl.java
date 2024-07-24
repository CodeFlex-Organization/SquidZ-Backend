package com.flabum.squidzbackend.iam.application.internal.commandservices;

import com.flabum.squidzbackend.iam.domain.model.commands.SeedRolesCommand;
import com.flabum.squidzbackend.iam.domain.model.entities.Role;
import com.flabum.squidzbackend.iam.domain.model.valueobjects.Roles;
import com.flabum.squidzbackend.iam.domain.services.RoleCommandService;
import com.flabum.squidzbackend.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@AllArgsConstructor
@Service
public class RoleCommandServiceImpl implements RoleCommandService {


    private final RoleRepository roleRepository;

    @Override
    public void execute(SeedRolesCommand command) {
        Arrays.stream(Roles.values()).forEach(role -> {
            if(!roleRepository.existsByName(role)){
                roleRepository.save(new Role(Roles.valueOf(role.name())));
            }
        });
    }
}
