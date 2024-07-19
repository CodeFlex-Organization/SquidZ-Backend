package com.flabum.squidzbackend.iam.interfaces.rest.user.resources;

import com.flabum.squidzbackend.iam.domain.model.valueobjects.EmailAddress;

public record UpdatePasswordResource(String currentPassword, String newPassword) {
}
