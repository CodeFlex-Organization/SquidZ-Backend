package com.flabum.squidzbackend.iam.interfaces.rest.user.resources;

public record UpdatePasswordRecoverAccountResource (String newPassword, String passwordRepeat){}
