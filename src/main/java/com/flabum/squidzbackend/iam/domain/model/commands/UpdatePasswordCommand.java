package com.flabum.squidzbackend.iam.domain.model.commands;

import com.flabum.squidzbackend.iam.domain.model.valueobjects.EmailAddress;

public record UpdatePasswordCommand(String currentPassword, String newPassword) {
}