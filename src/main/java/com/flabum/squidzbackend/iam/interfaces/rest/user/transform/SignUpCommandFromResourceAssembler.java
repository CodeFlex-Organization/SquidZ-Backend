package com.flabum.squidzbackend.iam.interfaces.rest.user.transform;


import com.flabum.squidzbackend.iam.domain.model.commands.SignUpCommand;
import com.flabum.squidzbackend.iam.domain.model.entities.Role;
import com.flabum.squidzbackend.iam.domain.model.valueobjects.EmailAddress;
import com.flabum.squidzbackend.iam.domain.model.valueobjects.Name;
import com.flabum.squidzbackend.iam.domain.model.valueobjects.PhoneNumber;
import com.flabum.squidzbackend.iam.interfaces.rest.user.resources.SignUpResource;

import java.util.ArrayList;


public class SignUpCommandFromResourceAssembler {

    public SignUpCommand toCommandFromResource(SignUpResource resource){
        var roles = resource.roles() != null ? resource.roles().stream().map(
                name -> Role.toRoleFromName(name)).toList(): new ArrayList<Role>();

        return new SignUpCommand(new Name(resource.name().firstName(), resource.name().lastName()), new EmailAddress(resource.emailAddress().address())
                , new PhoneNumber(resource.phoneNumber().countryCode(), resource.phoneNumber().number()), resource.password(), roles);
    }
}
