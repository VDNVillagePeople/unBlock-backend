package com.unblock.server.services;

import com.unblock.server.data.storage.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public interface UserService {
    void create(User user);

    Optional<User> getByUsername(String username);
}
