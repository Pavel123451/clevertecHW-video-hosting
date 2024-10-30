package ru.clevertec.clevertechwvideohosting.dto.channel;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class ChannelCreateDto {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String language;

    @NotBlank
    private String avatar;

    @NotNull
    @Min(1)
    private Long authorId;

    @NotNull
    @Min(1)
    private Long categoryId;
}
