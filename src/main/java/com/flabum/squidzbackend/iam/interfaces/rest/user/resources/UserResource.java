package com.flabum.squidzbackend.iam.interfaces.rest.user.resources;

import com.flabum.squidzbackend.iam.domain.model.valueobjects.EmailAddress;
import com.flabum.squidzbackend.iam.domain.model.valueobjects.Name;
import com.flabum.squidzbackend.iam.domain.model.valueobjects.PhoneNumber;

import java.util.List;

public record UserResource (Long id, Name name, PhoneNumber phoneNumber, EmailAddress emailAddress, List<String> roles){}

