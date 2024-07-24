package com.flabum.squidzbackend.iam.interfaces.rest.user.transform;

import com.flabum.squidzbackend.iam.domain.model.commands.UpdatePasswordCommand;
import com.flabum.squidzbackend.iam.domain.model.valueobjects.EmailAddress;
import com.flabum.squidzbackend.iam.interfaces.rest.user.resources.UpdatePasswordResource;

public class UpdatePasswordCommandFromResourceAssembler {

    public static UpdatePasswordCommand toCommandFromResource(UpdatePasswordResource resource) {
        return new UpdatePasswordCommand(resource.currentPassword(), resource.newPassword());
    }

}
