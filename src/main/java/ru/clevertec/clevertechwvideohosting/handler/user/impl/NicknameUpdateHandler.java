package ru.clevertec.clevertechwvideohosting.handler.user.impl;

import ru.clevertec.clevertechwvideohosting.dto.user.UserDto;
import ru.clevertec.clevertechwvideohosting.handler.user.UserUpdateHandler;
import ru.clevertec.clevertechwvideohosting.model.User;
import ru.clevertec.clevertechwvideohosting.repository.UserRepository;

public class NicknameUpdateHandler implements UserUpdateHandler {
    private final UserRepository userRepository;

    public NicknameUpdateHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void handle(User user, UserDto updateUser) {
        if (updateUser.getNickname() != null && !updateUser.getNickname().isBlank()) {
            if (userRepository.findByNickname(updateUser.getNickname()).isPresent()) {
                throw new IllegalStateException("User with this nickname already exists");
            }
            user.setNickname(updateUser.getNickname());
        }
    }
}
