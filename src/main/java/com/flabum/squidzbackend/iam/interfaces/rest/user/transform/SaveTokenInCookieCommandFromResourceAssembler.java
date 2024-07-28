package com.flabum.squidzbackend.iam.interfaces.rest.user.transform;

import com.flabum.squidzbackend.iam.domain.model.commands.SaveTokenInCookieCommand;
import com.flabum.squidzbackend.iam.interfaces.rest.user.resources.SaveTokenInCookieResource;

public class SaveTokenInCookieCommandFromResourceAssembler {

    public static SaveTokenInCookieCommand toCommandFromResource(SaveTokenInCookieResource resource){
        return new SaveTokenInCookieCommand(resource.token());
    }

}
