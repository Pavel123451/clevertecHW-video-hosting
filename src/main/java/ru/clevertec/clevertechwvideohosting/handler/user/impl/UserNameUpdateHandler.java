package ru.clevertec.clevertechwvideohosting.handler.user.impl;

import ru.clevertec.clevertechwvideohosting.dto.user.UserDto;
import ru.clevertec.clevertechwvideohosting.handler.user.UserUpdateHandler;
import ru.clevertec.clevertechwvideohosting.model.User;

public class UserNameUpdateHandler implements UserUpdateHandler {
    @Override
    public void handle(User user, UserDto updateUser) {
        if (updateUser.getName() != null && !updateUser.getName().isBlank()) {
            user.setName(updateUser.getName());
        }
    }
}
