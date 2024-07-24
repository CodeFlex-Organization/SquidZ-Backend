package com.flabum.squidzbackend.iam.application.internal.queryservices;

import com.flabum.squidzbackend.iam.domain.model.entities.Role;
import com.flabum.squidzbackend.iam.domain.model.queries.GetAllRolesQuery;
import com.flabum.squidzbackend.iam.domain.services.RoleQueryService;
import com.flabum.squidzbackend.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RoleQueryServiceImpl implements RoleQueryService {

    private RoleRepository roleRepository;


    @Override
    public List<Role> execute(GetAllRolesQuery query) {
        return roleRepository.findAll();
    }
}
