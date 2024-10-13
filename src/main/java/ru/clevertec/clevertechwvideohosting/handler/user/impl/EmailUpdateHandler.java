package ru.clevertec.clevertechwvideohosting.handler.user.impl;

import ru.clevertec.clevertechwvideohosting.dto.user.UserDto;
import ru.clevertec.clevertechwvideohosting.handler.user.UserUpdateHandler;
import ru.clevertec.clevertechwvideohosting.model.User;
import ru.clevertec.clevertechwvideohosting.repository.UserRepository;

public class EmailUpdateHandler implements UserUpdateHandler {
    private final UserRepository userRepository;

    public EmailUpdateHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void handle(User user, UserDto updateUser) {
        if (updateUser.getEmail() != null && !updateUser.getEmail().isBlank()) {
            if (userRepository.findByEmail(updateUser.getEmail()).isPresent()) {
                throw new IllegalStateException("User with this email already exists");
            }
            user.setEmail(updateUser.getEmail());
        }
    }
}
