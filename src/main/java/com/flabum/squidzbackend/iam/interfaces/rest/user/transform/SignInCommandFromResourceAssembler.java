package com.flabum.squidzbackend.iam.interfaces.rest.user.transform;


import com.flabum.squidzbackend.iam.domain.model.commands.SignInCommand;
import com.flabum.squidzbackend.iam.domain.model.valueobjects.EmailAddress;
import com.flabum.squidzbackend.iam.interfaces.rest.user.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource resource){
        return new SignInCommand(new EmailAddress(resource.email()), resource.password());
    }
}
