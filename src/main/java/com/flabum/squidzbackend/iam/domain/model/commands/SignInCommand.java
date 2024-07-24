package com.flabum.squidzbackend.iam.domain.model.commands;

import com.flabum.squidzbackend.iam.domain.model.valueobjects.EmailAddress;

public record SignInCommand(EmailAddress email, String password) {
}
