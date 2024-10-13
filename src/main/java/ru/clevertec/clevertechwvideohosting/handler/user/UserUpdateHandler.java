package ru.clevertec.clevertechwvideohosting.handler.user;

import ru.clevertec.clevertechwvideohosting.dto.user.UserDto;
import ru.clevertec.clevertechwvideohosting.model.User;

public interface UserUpdateHandler {
    void handle(User user, UserDto updateUser);
}
