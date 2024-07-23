package com.flabum.squidzbackend.iam.domain.model.commands;

import com.flabum.squidzbackend.iam.domain.model.valueobjects.Name;
import com.flabum.squidzbackend.iam.domain.model.valueobjects.PhoneNumber;

public record UpdateUserDataCommand(Name name, PhoneNumber phoneNumber) {
}
