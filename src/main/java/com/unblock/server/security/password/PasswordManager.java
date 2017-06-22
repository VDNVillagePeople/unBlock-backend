package com.unblock.server.security.password;

import org.springframework.stereotype.Service;

@Service
public interface PasswordManager {
    String getEncryptedPassword(String password);

    boolean checkPassword(String enteredPassword, String storedPassword);
}
