package ru.clevertec.clevertechwvideohosting.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class CreateUserDto {
    @NotBlank
    private String name;

    @NotBlank
    private String nickname;

    @NotBlank
    @Email
    private String email;
}
