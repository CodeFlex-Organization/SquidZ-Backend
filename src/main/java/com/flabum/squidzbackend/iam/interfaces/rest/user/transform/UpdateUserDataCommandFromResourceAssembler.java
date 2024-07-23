package com.flabum.squidzbackend.iam.interfaces.rest.user.transform;

import com.flabum.squidzbackend.iam.domain.model.commands.UpdateUserDataCommand;
import com.flabum.squidzbackend.iam.domain.model.valueobjects.Name;
import com.flabum.squidzbackend.iam.interfaces.rest.user.resources.UpdateUserDataResource;

public class UpdateUserDataCommandFromResourceAssembler {
    public static UpdateUserDataCommand toCommandFromResource( UpdateUserDataResource resource) {
        return new UpdateUserDataCommand(new Name(resource.firstName(), resource.lastName()), resource.phoneNumber());
    }
}
