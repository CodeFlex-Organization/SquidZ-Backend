package com.flabum.squidzbackend.iam.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Embeddable;

@Embeddable
public record Name(String firstName, String lastName) {
    public Name() {
        this(null, null);
    }

    public Name {
        if (firstName == null || firstName.isBlank()) {
            throw  new IllegalArgumentException("First name cannot be null or blank");
        }
        if (lastName == null || lastName.isBlank()) {
            throw  new IllegalArgumentException("Last name cannot be null or blank");
        }
    }

    @JsonIgnore
    public String getFullName(){
        return  firstName + " " + lastName;
    }
}
