package ru.clevertec.clevertechwvideohosting.util;

import ru.clevertec.clevertechwvideohosting.dto.user.UserDto;

public class UserUtil {

    public static boolean userDtoIsBlank(UserDto userDto) {
        String name = userDto.getName();
        String nickname = userDto.getNickname();
        String email = userDto.getEmail();
        return name == null || nickname == null || email == null
                || name.isBlank() || nickname.isBlank() || email.isBlank();
    }
}
