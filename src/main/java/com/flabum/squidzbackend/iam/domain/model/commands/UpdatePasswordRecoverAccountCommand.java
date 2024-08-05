package com.flabum.squidzbackend.iam.domain.model.commands;

public record UpdatePasswordRecoverAccountCommand(String newPassword, String repeatPassword) {
}
