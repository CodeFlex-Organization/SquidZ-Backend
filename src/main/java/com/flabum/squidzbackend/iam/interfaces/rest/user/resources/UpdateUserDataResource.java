package com.flabum.squidzbackend.iam.interfaces.rest.user.resources;

import com.flabum.squidzbackend.iam.domain.model.valueobjects.PhoneNumber;

public record UpdateUserDataResource(String firstName, String lastName, PhoneNumber phoneNumber) {
}
