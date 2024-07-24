package com.flabum.squidzbackend.iam.domain.model.commands;


import com.flabum.squidzbackend.iam.domain.model.entities.Role;
import com.flabum.squidzbackend.iam.domain.model.valueobjects.EmailAddress;
import com.flabum.squidzbackend.iam.domain.model.valueobjects.Name;
import com.flabum.squidzbackend.iam.domain.model.valueobjects.PhoneNumber;

import java.util.List;

public record SignUpCommand(Name name, EmailAddress emailAddress, PhoneNumber phoneNumber, String password, List<Role> roles) {
}
