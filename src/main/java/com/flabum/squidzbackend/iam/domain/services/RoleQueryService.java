package com.flabum.squidzbackend.iam.domain.services;

import com.flabum.squidzbackend.iam.domain.model.entities.Role;
import com.flabum.squidzbackend.iam.domain.model.queries.GetAllRolesQuery;

import java.util.List;

public interface RoleQueryService {

    List<Role> execute (GetAllRolesQuery query);


}
