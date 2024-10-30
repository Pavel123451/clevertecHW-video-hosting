package ru.clevertec.clevertechwvideohosting.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPartialUpdateDto {
    private String name;
    private String nickname;
    private String email;
}
