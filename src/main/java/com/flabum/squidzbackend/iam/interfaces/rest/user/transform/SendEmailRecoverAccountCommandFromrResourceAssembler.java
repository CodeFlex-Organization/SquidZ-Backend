package com.flabum.squidzbackend.iam.interfaces.rest.user.transform;

import com.flabum.squidzbackend.iam.domain.model.commands.SendEmailRecoverAccountCommand;
import com.flabum.squidzbackend.iam.domain.model.valueobjects.EmailAddress;
import com.flabum.squidzbackend.iam.interfaces.rest.user.resources.SendEmailRecoverAccountResource;

public class SendEmailRecoverAccountCommandFromrResourceAssembler {

    public static SendEmailRecoverAccountCommand toCommandFromResource(SendEmailRecoverAccountResource resource){
        return new SendEmailRecoverAccountCommand(new EmailAddress(resource.email()));
    }
}
