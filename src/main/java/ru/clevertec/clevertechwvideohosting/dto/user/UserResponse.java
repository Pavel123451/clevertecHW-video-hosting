package ru.clevertec.clevertechwvideohosting.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class UserResponse {
    private Long id;
    private String name;
    private String nickname;
    private String email;
}
