package com.flabum.squidzbackend.iam.interfaces.rest.user.transform;

import com.flabum.squidzbackend.iam.domain.model.commands.UpdatePasswordRecoverAccountCommand;
import com.flabum.squidzbackend.iam.interfaces.rest.user.resources.UpdatePasswordRecoverAccountResource;

public class UpdatePasswordRecoverAccountCommandFromResourceAssembler {

    public static UpdatePasswordRecoverAccountCommand toCommandFromResource(UpdatePasswordRecoverAccountResource resource) {
        return new UpdatePasswordRecoverAccountCommand(resource.newPassword(), resource.passwordRepeat());
    }
}
