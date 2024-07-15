package com.flabum.squidzbackend.iam.interfaces.rest.role.transform;


import com.flabum.squidzbackend.iam.domain.model.entities.Role;
import com.flabum.squidzbackend.iam.interfaces.rest.role.resource.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role){
        return new RoleResource(role.getId(), role.getStringName());
    }
}
