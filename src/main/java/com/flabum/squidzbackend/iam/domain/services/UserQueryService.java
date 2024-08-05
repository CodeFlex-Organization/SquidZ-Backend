package com.flabum.squidzbackend.iam.domain.services;

import com.flabum.squidzbackend.iam.domain.model.aggregates.User;
import com.flabum.squidzbackend.iam.domain.model.queries.GetUserByJwtQuery;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface UserQueryService {
    Optional<User> execute(GetUserByJwtQuery query, HttpServletRequest request);
}
